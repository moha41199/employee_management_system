package com.FBook.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdminUser {
	@Id
private String username;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
private String password;
}
