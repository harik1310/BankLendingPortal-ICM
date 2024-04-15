package com.cognizant.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class Users {
	@Id
	@Column(name="user_name")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;
	@Column(name="is_Account_Locked")
	private boolean isAccountLocked;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isAccountLocked() {
		return isAccountLocked;
	}
	public void setAccountLocked(boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}
	@Override
	public int hashCode() {
		return Objects.hash(isAccountLocked, password, role, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return isAccountLocked == other.isAccountLocked && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "Users [userName=" + userName + ", password=" + password + ", role=" + role + ", isAccountLocked="
				+ isAccountLocked + "]";
	}
	

}
