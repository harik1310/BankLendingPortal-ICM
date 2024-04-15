package com.cognizant.dto;


public class UserDTO {

	private String userName;
	private String password;
	private String role;
	private boolean isAccountLocked;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", password=" + password + ", role=" + role + ", isAccountLocked="
				+ isAccountLocked + "]";
	}
	
	
}
