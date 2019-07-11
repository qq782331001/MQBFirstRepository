package com.mqb.springbootspringsecurity.service;

import com.mqb.springbootspringsecurity.dao.RolesMapper;
import com.mqb.springbootspringsecurity.pojo.RolesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolesService{

	@Autowired
	RolesMapper roleMapper;

	public List<RolesEntity> roles() {
		return roleMapper.roles();
	}

	public List<RolesEntity> getRolesByUrl(String url) {
		// TODO Auto-generated method stub
		return roleMapper.getRolesByUrl(url);
	}
	public boolean delRelations(long roleId, long deletePerID) {
		// TODO Auto-generated method stub
		int result = roleMapper.delRelations(roleId, deletePerID);
		return result > 0;
	}

	@Transactional
	public boolean addRelations(List<String> rolePermRelationList, long roleId) {
		// TODO Auto-generated method stub
		int result = roleMapper.addRelations(rolePermRelationList, roleId);
		return result > 0;
	}


	public List<RolesEntity> findRoles() {
		// TODO Auto-generated method stub
		return roleMapper.findRoles();
	}

	public List<RolesEntity> findRolesAll() {
		// TODO Auto-generated method stub
		return roleMapper.findRoles();
	}

	@Transactional
	public boolean addRole(RolesEntity rolesEntity) {
		System.out.println("------------>" + rolesEntity.toString());
		int result = roleMapper.addRole(rolesEntity);

		return result > 0;
	}

	@Transactional
	public boolean updateRoleByRoleId(long roleId, RolesEntity rolesEntity) {

		int result = roleMapper.updateRoleByRoleId(roleId, rolesEntity);

		return result > 0;
	}

	public boolean delRoleByRoleId(long roleId) {

		int result = roleMapper.delRoleByRoleId(roleId);

		return result > 0;
	}

}
