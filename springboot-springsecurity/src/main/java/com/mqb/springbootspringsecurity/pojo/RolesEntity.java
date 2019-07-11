package com.mqb.springbootspringsecurity.pojo;

import java.io.Serializable;
import java.util.Date;

public class RolesEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String rolename;
	private String describes;
	private int locked;
	private int rolesType; // 0 admin 1默认 2自定义
	private long companyId;
	private Date createTime;
	private Date modifyTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public int getRolesType() {
		return rolesType;
	}

	public void setRolesType(int rolesType) {
		this.rolesType = rolesType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "RolesEntity [id=" + id + ", rolename=" + rolename + ", describes=" + describes + ", locked=" + locked
				+ ", rolesType=" + rolesType + ", companyId=" + companyId + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + "]";
	}

}
