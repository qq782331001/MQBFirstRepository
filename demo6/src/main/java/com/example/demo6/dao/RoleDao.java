package com.example.demo6.dao;

import com.example.demo6.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao extends  JpaSpecificationExecutor<Role> , JpaRepository<Role,Long> {
}
