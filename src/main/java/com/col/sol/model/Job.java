package com.col.sol.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Job extends BaseDomain{
	@Id
	@GeneratedValue
	int jobid;
	String title,jobdesc,qualification,jobprofile;
	String status;
	Date postdate;
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	
	public String getJobprofile() {
		return jobprofile;
	}
	public void setJobprofile(String jobprofile) {
		this.jobprofile = jobprofile;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJobdesc() {
		return jobdesc;
	}
	public void setJobdesc(String jobdesc) {
		this.jobdesc = jobdesc;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if(status==null){
			status=new String("V");
		}
		this.status = status;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		if(postdate==null)
		{
			postdate=new Date(System.currentTimeMillis());
		}
		
		this.postdate = postdate;
	}
	

}
