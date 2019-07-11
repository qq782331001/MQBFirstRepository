package com.mqb.springbootspringsecurity.dao;

import com.mqb.springbootspringsecurity.pojo.RolesEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesMapper {
	List<RolesEntity> roles();
	List<RolesEntity> getRolesByUrl(String url);
	public int addRole(@Param("roles") RolesEntity rolesEntity);
	public int updateRoleByRoleId(@Param("roleId") long roleId, @Param("roles") RolesEntity rolesEntity);
	public int delRoleByRoleId(@Param("roleId") long roleId);
	public List<RolesEntity> findRolesAll();
	public List<RolesEntity> findRoles();
	public int addRelations(@Param("rolePermRelation") List<String> rolePermRelationList, @Param("roleId") long roleId);
	public int delRelations(@Param("roleId") long roleId, @Param("deletePerID") long deletePerID);

}
