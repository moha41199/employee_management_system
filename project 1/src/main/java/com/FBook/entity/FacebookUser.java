package com.FBook.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity	//it is used to map entity class on table. which table? by default class name will be table name i.e. table name will be FacebookUser. if want to change table name then use @Table
@Table(name="FBookUser")
@NamedQueries({
	@NamedQuery(
			name="loginData",
			query = "from FacebookUser fu where fu.email= :email and fu.password= :password"
			)
})
public class FacebookUser {
	private String name;
	private String password;
	@Id
	private String email;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
