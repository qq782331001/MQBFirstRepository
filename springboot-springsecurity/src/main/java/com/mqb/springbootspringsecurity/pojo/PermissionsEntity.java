package com.mqb.springbootspringsecurity.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PermissionsEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String url; // 请求路径
	private String describes;// 功能描述
	private int locked; // 是否锁定
	private Date createTime;// 创建时间
	private Date modifyTime;// 修改时间
	private List<RolesEntity> roles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public List<RolesEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RolesEntity> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "PermissionsEntity [id=" + id + ", url=" + url + ", describes=" + describes + ", locked=" + locked
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}

}
