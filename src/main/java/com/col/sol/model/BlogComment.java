package com.col.sol.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class BlogComment {
	@Id
	@GeneratedValue
	int id;
	int BlogId,userid1;
	String comment1,username1;
	Date commentdate1;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBlogId() {
		return BlogId;
	}
	public void setBlogId(int blogId) {
		BlogId = blogId;
	}
	public int getUserid1() {
		return userid1;
	}
	public void setUserid1(int userid1) {
		this.userid1 = userid1;
	}
	public String getComment1() {
		return comment1;
	}
	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}
	public String getUsername1() {
		return username1;
	}
	public void setUsername1(String username1) {
		this.username1 = username1;
	}
	public Date getCommentdate1() {
		return commentdate1;
	}
	public void setCommentdate1(Date commentdate1) {
		this.commentdate1 = commentdate1;
	}
		
}
