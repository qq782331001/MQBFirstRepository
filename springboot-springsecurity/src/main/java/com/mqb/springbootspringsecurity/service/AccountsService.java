package com.mqb.springbootspringsecurity.service;

import com.mqb.springbootspringsecurity.config.CustomerUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AccountsService extends UserDetailsService {

	public CustomerUserDetails loadUserByUsername(String username);

    void updatePassword(String username, String newpassword);

    CustomerUserDetails loadUserByPassword(String encode);

    void resetPassword(String username, String resetPassword);
}
