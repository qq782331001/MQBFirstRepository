package com.mqb.springbootspringsecurity.dao;

import com.mqb.springbootspringsecurity.config.CustomerUserDetails;
import com.mqb.springbootspringsecurity.pojo.AccountsEntity;
import com.mqb.springbootspringsecurity.pojo.RolesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountsMapper {

	AccountsEntity loadUserByUsername(@Param("username") String username);

	List<RolesEntity> getRolesByUserId(@Param("id") Long id);

	List<RolesEntity> getRolesByUserName(@Param("username") String username);

	CustomerUserDetails loadUserByPassword(@Param("encode") String encode);

	void updatePassword(@Param("username") String username, @Param("newpassword") String newpassword);

	void resetPassword(@Param("username") String username, @Param("resetPassword") String resetPassword);
}
