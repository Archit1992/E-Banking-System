package main.java.vo;

import java.util.Date;

public class RegBean {
	
	private String username;
	private String password;
	private String email;
	private String gender;
	private Date regDate;
	private Date birthdate;
	private String id;
	
	public String getUsername() {
		return username;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate() {
		this.regDate = new Date();
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
