package com.mqb.springbootspringsecurity.pojo;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "user_accounts")
public class AccountsEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String uuid; // 由用户名+手机号+邮箱生成的登录账号
	private String username; // 用户名
	private String phone; // 电话
	private String email; // 邮件
	private String password; // 密码
	private String salt; // 盐(前端不需要管)
	private int locked = 0; // 是否锁定（0表示未锁定，可用，1表示不可用）
	private long companyId; // 公司id
	private boolean rememberMe; // 记住我
	private Date createTime; // 创建时间
	private Date modifyTime; // 修改时间

	private List<RolesEntity> roles;

	public Boolean isLocked() {
		return locked != 0;
	}

	public long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RolesEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RolesEntity> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "AccountsEntity [id=" + id + ", uuid=" + uuid + ", phone=" + phone + ", email=" + email + ", password="
				+ password + ", salt=" + salt + ", locked=" + locked + ", rememberMe=" + rememberMe + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + "]";
	}

}
