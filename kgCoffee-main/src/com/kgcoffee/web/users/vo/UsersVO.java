package com.kgcoffee.web.users.vo;

import java.util.Date;

public class UsersVO {
	
	private String user_id;
	private String user_pw;
	private String user_name;
	private Date birthday;
	private String tel;
	
	public UsersVO() { }
	
	public UsersVO(String user_id, String user_pw, String user_name, Date birthday, String tel) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.birthday = birthday;
		this.tel = tel;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
