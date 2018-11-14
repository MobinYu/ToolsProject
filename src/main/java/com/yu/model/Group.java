package com.yu.model;

public class Group {
	
	private String id;
	private String name;
	private String duty;
	private String leader;
	private String identifier;
	private String level;
	private String foundingTime;
	private String introduction;
	
	public Group() {
		super();
	}
	
	public Group(String id, String name, String duty, String leader, String identifier, String level, String foundingTime, String introduction) {
		super();
		this.id = id;
		this.name = name;
		this.duty = duty;
		this.leader = leader;
		this.identifier = identifier;
		this.level = level;
		this.foundingTime = foundingTime;
		this.introduction = introduction;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFoundingTime() {
		return foundingTime;
	}

	public void setFoundingTime(String foundingTime) {
		this.foundingTime = foundingTime;
	}

	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}
