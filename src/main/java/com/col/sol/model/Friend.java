package com.col.sol.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Entity
@Table
@Repository
public class Friend extends BaseDomain {
	@Id
	@GeneratedValue
	int friendid;
	int userid;
	String status,friendid1,userid1;
	char is_online;
	//char is_offline,offline,;
	
	
	public char getIs_offline() {
		return is_online;
	}
	public void setIs_offline(char is_offline) {
		this.is_online = is_offline;
	}
/*	public char getOffline() {
		return offline;
	}
	public void setOffline(char offline) {
		this.offline = offline;
	}
	public char getOnline() {
		return online;
	}
	public void setOnline(char online) {
		this.online = online;
	}*/
	public String getFriendid1() {
		return friendid1;
	}
	public void setFriendid1(String friendid1) {
		this.friendid1 = friendid1;
	}
	public String getUserid1() {
		return userid1;
	}
	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}
	public int getFriendid() {
		return friendid;
	}
	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
