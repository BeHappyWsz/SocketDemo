package com.wsz.SocketDemo.entity;

import java.io.Serializable;
/**
 * 用户user类
 * @author wsz
 * @date 2018年3月3日
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;
	
	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
}
