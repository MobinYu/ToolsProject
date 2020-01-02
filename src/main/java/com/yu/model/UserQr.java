package com.yu.model;

public class UserQr extends User{
	
	private String qrPath;

	public UserQr(String qrPath, String id, String name, int age) {
		super();
		this.qrPath = qrPath;
		super.setId(id);
		super.setName(name);
		super.setAge(age);
	}

	public String getQrPath() {
		return qrPath;
	}

	public void setQrPath(String qrPath) {
		this.qrPath = qrPath;
	}
	
}
