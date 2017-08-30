package com.col.sol;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.sol.dao.JobDAO;
import com.col.sol.model.Job;

public class JobTestCase {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.col.sol");
		context.refresh();
		JobDAO jobdao=(JobDAO) context.getBean("jobDAO");
		Job job=new Job();
		/*job.setJobid(1003);
		job.setJobprofile("job ");
		job.setQualification("MCA");
		job.setStatus("na");
		jobdao.insertjob(job);*/
		
		/*List<Job>j=jobdao.getAllJob();
		for(Job h:j){
		System.out.println(h.getJobid());
		System.out.println(h.getQualification());
		}*/
		//jobdao.delete(1002);
		Job j=jobdao.getSingleJob(1001);
	//	j.setStatus('A');
		jobdao.insertjob(j);
		
	}

}
