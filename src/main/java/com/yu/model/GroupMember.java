package com.yu.model;

public class GroupMember {

	private String id;
	private String groupId;
	private String name;
	private String sex;
	private String iDNumber;
	private String phoneNo;
	
	public GroupMember() {
		super();
	}
	
	public GroupMember(String id, String groupId, String name, String sex, String iDNumber, String phoneNo) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.name = name;
		this.sex = sex;
		this.iDNumber = iDNumber;
		this.phoneNo = phoneNo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIDNumber() {
		return iDNumber;
	}
	public void setIDNumber(String iDNumber) {
		this.iDNumber = iDNumber;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
}
