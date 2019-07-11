package com.mqb.springbootspringsecurity.config;

import com.mqb.springbootspringsecurity.dao.PermissionsMapper;
import com.mqb.springbootspringsecurity.pojo.PermissionsEntity;
import com.mqb.springbootspringsecurity.pojo.RolesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private PermissionsMapper permissionsMapper;

	// resourceMap及为key-url，value-Collection<ConfigAttribute>,资源权限对应Map
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public void MyInvocationSecurityMetadataSourceService(PermissionsMapper permissionsMapper) {
		this.permissionsMapper = permissionsMapper;
		System.out.println("加载MyInvocationSecurityMetadataSourceService..." + permissionsMapper);
		loadResourceDefine();
	}

	// 加载所有资源与权限的关系
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			Collection<ConfigAttribute> array;
			ConfigAttribute cfg = null;
			List<PermissionsEntity> permissions = permissionsMapper.getAllPermission();
			System.out.println("permissions的size" + permissions.size());
			// 加载资源对应的权限
			for (PermissionsEntity permission : permissions) {
				System.out.println("permission的对应的url信息:" + permission.getUrl());
				array = new ArrayList<>();
				for (RolesEntity role : permission.getRoles()) {
					cfg = new SecurityConfig(role.getRolename());
					array.add(cfg);
				}
				System.out.println("权限=" + array);
				resourceMap.put(permission.getUrl(), array);
			}
		}
	}

	// 由资源路径获得权限
	// object为请求的资源路径
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object是一个URL，被用户请求的url
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		System.out.println("requestUrl is " + requestUrl);

		int firstQuestionMarkIndex = requestUrl.indexOf("?");
		// 如果请求的资源路径有？后面的参数，则将？后面的切掉，以免拒绝访问
		if (firstQuestionMarkIndex != -1) {
			requestUrl = requestUrl.substring(0, firstQuestionMarkIndex);
		}

		if (resourceMap == null) {
			loadResourceDefine();
		}
		//
		Iterator<String> ite = resourceMap.keySet().iterator();
		// 根据资源路径获得其所需的权限
		while (ite.hasNext()) {
			String resURL = ite.next();

			if (resURL.equals(requestUrl)) {
				return resourceMap.get(resURL);
			}
		}
		return SecurityConfig.createList("ROLE_LOGIN");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return FilterInvocation.class.isAssignableFrom(aClass);
	}
}
