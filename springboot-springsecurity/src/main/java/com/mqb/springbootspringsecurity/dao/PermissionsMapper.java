package com.mqb.springbootspringsecurity.dao;

import com.mqb.springbootspringsecurity.pojo.PermissionsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionsMapper {

	List<PermissionsEntity> getAllPermission();
	public List<PermissionsEntity> findPermissionsAll();

	public int addPermissions(@Param("permissions") PermissionsEntity permissionsEntity);

	public int updatePermissionsById(@Param("permissionsId") long permissionsId, @Param("permissions") PermissionsEntity permissionsEntity);

	public int delPermissionsById(@Param("permissionsId") long permissionsId);


	List<PermissionsEntity> findPermissionsAll2();

	List<PermissionsEntity> findPermissionsAll3();

	List<PermissionsEntity> findPermissionsAllByparentId1();

	List<PermissionsEntity> findPermissionsAllByparentId2();
}
