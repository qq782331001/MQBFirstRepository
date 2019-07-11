package com.mqb.springbootspringsecurity.service.impl;

import com.mqb.springbootspringsecurity.config.CustomerUserDetails;
import com.mqb.springbootspringsecurity.dao.AccountsMapper;
import com.mqb.springbootspringsecurity.pojo.AccountsEntity;
import com.mqb.springbootspringsecurity.pojo.RolesEntity;
import com.mqb.springbootspringsecurity.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountsServiceImpl implements AccountsService {
	@Autowired
	AccountsMapper accountsMapper;

	@Override
	public CustomerUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AccountsEntity account = accountsMapper.loadUserByUsername(username);
		if (account == null)
			throw new UsernameNotFoundException("Username not found");
		System.out.println("当前需要认证的登录名:" + account.getUsername());
		//存放权限
		List<GrantedAuthority> authorities = new ArrayList<>();
		//存放角色
		List<RolesEntity> rolelist = account.getRoles();
		if (rolelist != null) {

			for (RolesEntity role : rolelist) {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRolename());
				// 此处将权限信息添加到 GrantedAuthority对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
				authorities.add(grantedAuthority);
			}
		}
		//CustomerUserDetails自定义实现userdetails的类
		return new CustomerUserDetails(account, authorities);
	}

	@Override
	public void updatePassword(String username, String newpassword) {
		accountsMapper.updatePassword(username,newpassword);
	}

	/**
	 *
	 * 根据密码查找用户
	 * @param encode
	 * @return
	 */
	@Override
	public CustomerUserDetails loadUserByPassword(String encode) {
		CustomerUserDetails customerUserDetails = 	accountsMapper.loadUserByPassword(encode);
		return customerUserDetails;
	}

	/**
	 * 重置密码  密码为123456
	 * @param username
	 * @param resetPassword 密码
	 */
	@Override
	public void resetPassword(String username, String resetPassword) {
		accountsMapper.resetPassword(username,resetPassword);
	}
}
