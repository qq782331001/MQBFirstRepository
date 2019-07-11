package com.mqb.springbootspringsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mqb.springbootspringsecurity.service.AccountsService;
import com.mqb.springbootspringsecurity.utils.HrUtils;
import com.mqb.springbootspringsecurity.utils.ProjectError;
import com.mqb.springbootspringsecurity.utils.ResultSuccess;
import com.mqb.springbootspringsecurity.utils.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountsService accountService;
	@Autowired
	CustomMetadataSource customMetadataSource;
	@Autowired
	UrlAccessDecisionManager urlAccessDecisionManager;
	@Autowired
	AuthenticationAccessDeniedHandler deniedHandler;
	@Resource
	private DataSource dataSource;

	/*生成密码
	 * public static void main(String[] args) {
	 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 * String newpass = passwordEncoder.encode("123456");
	 * System.out.println(newpass); }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/index.html", "/static/**", "/login_p", "/favicon.ico", "/login.html");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O o) {
				System.out.println("开始验证++++++++");
				//
				o.setSecurityMetadataSource(customMetadataSource);
				//
				o.setAccessDecisionManager(urlAccessDecisionManager);
				return o;
			}
		}).and().formLogin().loginPage("/login_p").loginProcessingUrl("/login").usernameParameter("username")
				.passwordParameter("password").failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
														AuthenticationException e) throws IOException {
						resp.setContentType("application/json;charset=utf-8");
						ProjectError projectError = null;
						if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
							projectError = ProjectError.failure(StatusCode.USER_LOGIN_ERROR);
						} else if (e instanceof LockedException) {
							projectError = ProjectError.failure(StatusCode.USER_ACCOUNT_LOCKED);
						} else if (e instanceof CredentialsExpiredException) {
							projectError = ProjectError.failure(StatusCode.PASSWRD_HAS_OUTTIME);
						} else if (e instanceof AccountExpiredException) {
							projectError = ProjectError.failure(StatusCode.USER_HAS_OUTTIME);
						} else if (e instanceof DisabledException) {
							projectError = ProjectError.failure(StatusCode.USER_ACCOUNT_FORBIDDEN);
						} else {
							projectError = ProjectError.failure(StatusCode.USER_LOGIN_FAILED);
						}
						resp.setStatus(401);
						ObjectMapper om = new ObjectMapper();
						PrintWriter out = resp.getWriter();
						out.write(om.writeValueAsString(projectError));
						out.flush();
						out.close();
					}
				}).successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
                                                        Authentication auth) throws IOException {
						resp.setContentType("application/json;charset=utf-8");
						ResultSuccess success = new ResultSuccess();
						success.setMsg("登录成功!");

						success.setData(HrUtils.getCurrentHr());
						System.out.println(HrUtils.getCurrentHr());
						//将数据转换为json
						ObjectMapper om = new ObjectMapper();

						PrintWriter out = resp.getWriter();
						out.write(om.writeValueAsString(success));
						out.flush();
						out.close();

					}
				}).permitAll().and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout")
				.logoutSuccessHandler(new LogoutSuccessHandler() {
					@Override
					public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp,
                                                Authentication authentication) throws IOException, ServletException {
						resp.setContentType("application/json;charset=utf-8");
						ResultSuccess success = new ResultSuccess();
						success.setMsg("注销成功!");
						ObjectMapper om = new ObjectMapper();
						PrintWriter out = resp.getWriter();
						out.write(om.writeValueAsString(success));
						out.flush();
						out.close();
					}
				}).permitAll().and().rememberMe().tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(1209600).and().csrf().disable().exceptionHandling()
				.accessDeniedHandler(deniedHandler);

		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl("/login?expired");

	}

	// 如果采用持久化 token 的方法则需要指定保存token的方法
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

}
