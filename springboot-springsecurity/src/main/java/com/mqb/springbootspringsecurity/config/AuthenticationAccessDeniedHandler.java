package com.mqb.springbootspringsecurity.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e)
			throws IOException {
		resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		System.out.println("----------------------" + 2222);
		out.flush();
		out.close();
	}
}