package com.mqb.springbootspringsecurity.service;

import com.mqb.springbootspringsecurity.dao.PermissionsMapper;
import com.mqb.springbootspringsecurity.pojo.PermissionsEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class PermissionsService {
	PermissionsMapper permissionsMapper;

	// @Cacheable(key = "#root.methodName")
	public List<PermissionsEntity> getAllPermission() {
		return permissionsMapper.getAllPermission();
	}

	public List<PermissionsEntity> findPermissionsAll() {
		// TODO Auto-generated method stub
		return permissionsMapper.findPermissionsAll();
	}

	@Transactional
	public boolean addPermissions(PermissionsEntity permissionsEntity) {
		// TODO Auto-generated method stub
		int result = permissionsMapper.addPermissions(permissionsEntity);
		return result > 0;
	}

	@Transactional
	public boolean updatePermissionsById(long permissionsId, PermissionsEntity permissionsEntity) {
		// TODO Auto-generated method stub
		int result = permissionsMapper.updatePermissionsById(permissionsId, permissionsEntity);
		return result > 0;
	}

	public boolean delPermissionsById(long permissionsId) {
		// TODO Auto-generated method stub
		int result = permissionsMapper.delPermissionsById(permissionsId);
		return result > 0;
	}

	public List<PermissionsEntity> findPermissionsAllByparentId1() {
		return permissionsMapper.findPermissionsAllByparentId1();
	}

	public List<PermissionsEntity> findPermissionsAllByparentId2() {
		return permissionsMapper.findPermissionsAllByparentId2();
	}
}
