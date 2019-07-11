package com.mqb.springbootspringsecurity.utils;

import com.mqb.springbootspringsecurity.config.CustomerUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {
	public static CustomerUserDetails getCurrentHr() {
		return (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}

