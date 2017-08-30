package com.col.sol.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.col.sol.dao.JobDAO;
import com.col.sol.model.Job;
import com.col.sol.model.Job_Applied;
import com.col.sol.model.blog1;
@RestController
public class JobController {
	
	@Autowired
	JobDAO jobdao;
	@Autowired
	Job_Applied jobapplication;
    
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping(value="/getjob",method=RequestMethod.GET)
	public ResponseEntity <List<Job>> getallre(){
		List<Job>k=jobdao.getAllJob();
		return new ResponseEntity<List<Job>>(k,HttpStatus.OK);
		
		
	}
	@RequestMapping(value="/getappliedjob1/",method=RequestMethod.GET)
	public ResponseEntity<List<Job>>getallre1(HttpSession session){
		String users=(String)session.getAttribute("loggedInUserId");
		
		List<Job>k1=jobdao.getAllApplied(users);
		return new ResponseEntity<List<Job>>(k1,HttpStatus.OK);
	}
	
	@RequestMapping(value="/insertjob",method=RequestMethod.POST)
	public void insertjob(@RequestBody Job job){
	 // job.setJobid(108);
	  //job.setJobdesc("Management is good");
	  //job.setJobprofile("Manager");
	  //job.setQualification("MCA");
	  job.setStatus("V");
	  
		jobdao.insertjob(job);
		
	}
	@RequestMapping(value="/deletejob/{did}",method=RequestMethod.DELETE)
	public void deletejob(@PathVariable ("did")int did){
		  jobdao.delete(did);
		
	}
	
	@RequestMapping(value="/updatejob/{uid}",method=RequestMethod.PUT)
	public void updatejob(@PathVariable("uid")int uid,@RequestBody Job job){
		Job j=jobdao.getSingleJob(uid);
		j.setStatus(job.getStatus());
		jobdao.insertjob(j);
		
		
	}
	@RequestMapping(value="/postjob",method=RequestMethod.POST)	
public Job postAJob(@RequestBody Job job)
{ System.out.println("-------postjob");
job.setStatus("V");//1.vacant 2.F-Filled 3.P-Pending
	if(jobdao.save(job))
	{
		job.setErrorCode("200");
		job.setErrorMessage("Successfully posted the job");
	}
	else
	{
		job.setErrorCode("404");
		job.setErrorMessage("couldnot post job ");
	}
	return job;
}
@RequestMapping(value="/getjobdetails/{jobid}",method=RequestMethod.GET)	
public ResponseEntity<Job>getJobDetails(@PathVariable("jobid")int jobid){
	System.out.println("-------->getjobdetails"+jobid);
	Job job=jobdao.getSingleJob(jobid);
	if(job==null){
		job=new Job();
		job.setErrorCode("404");
		job.setErrorMessage("job not available for this id: "+jobid);
	}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}

@RequestMapping(value="/updatejob",method=RequestMethod.PUT)
public ResponseEntity<Job>updatejob(@RequestBody Job job){
	if(jobdao.update(job)==false){
		job.setErrorCode("404");
		job.setErrorMessage("Not able to update a job");
	}
	else
	{
		job.setErrorCode("200");
		job.setErrorMessage("successfully updated the job");
	}
	return new ResponseEntity<Job>(job,HttpStatus.OK);
}
@RequestMapping(value = "/applyForJob/{jobid}", method = RequestMethod.POST)
public ResponseEntity<Job_Applied>applyforjob(@PathVariable("jobid")int jobid)
{
	System.out.println("---------------->in applyforjob");
	
	String loggedInUserID=(String)httpSession.getAttribute("loggedInUserId");
	if(loggedInUserID==null||loggedInUserID.isEmpty()){
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage("you have loggin to apply for a job");
		
	}
	else{
		if(isUserAppliedForTheJob(loggedInUserID,jobid)==false){
		 jobapplication.setJob_id(jobid);
		 jobapplication.setUserid(loggedInUserID);
		 jobapplication.setStatus("N");//N-newly applied,c-call,s-selected
		 jobapplication.setDate_applied(new Date(System.currentTimeMillis()));
	
			jobapplication.setErrorCode("200");
			jobapplication.setErrorMessage("you have sucessfully applied for the job:" +jobid);
			jobdao.save(jobapplication);
		}
		else
		{
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage("you already applied for the job"+jobid);
		
		}
		
		
	}
	return new ResponseEntity<Job_Applied>(jobapplication, HttpStatus.OK);	
}
private boolean isUserAppliedForTheJob(String userid,int jobid){
	if(jobdao.getjobapplication(userid, jobid)==null){
		return false;
	}
	return true;
}
/*
@RequestMapping(value = "/selectUser/{userID}/{jobID}/{remarks}", method = RequestMethod.PUT)
public ResponseEntity<Job_Applied> selectUser(@PathVariable("userID") String userID,
		@PathVariable("jobID") int jobID, @PathVariable("remarks") String remarks) {
	System.out.println("Starting of the method selectUser");
	jobapplication = updateJobApplicationStatus(userID, jobID, "S", remarks);

	return new ResponseEntity<Job_Applied>(jobapplication, HttpStatus.OK);
}
@RequestMapping(value = "/callForInterview/{userID}/{jobID}/{remarks}", method = RequestMethod.PUT)
public ResponseEntity<Job_Applied> callForInterview(@PathVariable("userID") String userID,
		@PathVariable("jobID") int jobID, @PathVariable("remarks") String remarks) {
	System.out.println("Starting of the method canCallForInterview");

	jobapplication = updateJobApplicationStatus(userID, jobID, "C", remarks);

	return new ResponseEntity<Job_Applied>(jobapplication, HttpStatus.OK);
}

@RequestMapping(value = "/rejectJobApplication/{userID}/{jobID}/{remarks}", method = RequestMethod.PUT)
public ResponseEntity<Job_Applied> rejectJobApplication(@PathVariable("userID") String userID,
		@PathVariable("jobID") int jobID, @PathVariable("remarks") String remarks) {
	System.out.println("Starting of the method rejectJobApplication");
	jobapplication = updateJobApplicationStatus(userID, jobID, "R", remarks);

	return new ResponseEntity<Job_Applied>(jobapplication, HttpStatus.OK);
}




private Job_Applied updateJobApplicationStatus(String userID, int jobID, String status, String remarks) {
	System.out.println("Starting of the method updateJobApplicationStatus");
//userid name will be passed
	if (isUserAppliedForTheJob(userID, jobID) == false) {
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage(userID + " not applied for the job " + jobID);
		return jobapplication;
	}

	String loggedInUserRole = (String) httpSession.getAttribute("loggedInUserRole");
	System.out.println("loggedInUserRole:" + loggedInUserRole);
	if (loggedInUserRole == null || loggedInUserRole.isEmpty()) {
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage("You are not logged in");
		return jobapplication;
	}

	if (!loggedInUserRole.equalsIgnoreCase("admin")) {
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage("You are not admin.  You can not do this Operation");
		return jobapplication;
	}
	jobapplication = jobdao.getjobapplication(userID, jobID);

	jobapplication.setStatus(status);
	jobapplication.setRemarks(remarks);
	if (jobdao.update(jobapplication)) {
		jobapplication.setErrorCode("200");
		jobapplication.setErrorMessage("Successfully updated the status as " + status);
		System.out.println("Successfully updated the status as " + status);
	} else {
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage("Not able to update the status " + status);
		System.out.println("Not able to update the status" + status);
	}

	return jobapplication;
}

/*@RequestMapping(value = "/getMyAppliedJobs/", method = RequestMethod.GET)

public ResponseEntity<List<Job>> getMyAppliedJobs() {
	System.out.println("Starting of the method getMyAppliedJobs");
	String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
	List<Job> jobs = new ArrayList<Job>();

	if (loggedInUserID == null || loggedInUserID.isEmpty()) {
		job.setErrorCode("404");
		job.setErrorMessage("You have to login to see you applied jobs");
		jobs.add(job);

	} else {
		jobs = jobdao.getMyAppliedJobs(loggedInUserID);
	}

	return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
}*/

@RequestMapping(value="/listofjob",method=RequestMethod.GET)
public ResponseEntity<?>getAllBlogs(HttpSession session){
	String users=(String)session.getAttribute("loggedInUserId");
	if(users==null){
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage("UnAuthorized user");
		return new ResponseEntity<Job_Applied>(jobapplication,HttpStatus.UNAUTHORIZED);
	}
	List<Job_Applied>blogpost=jobdao.getAllJobsStatusN();
	return new ResponseEntity<List<Job_Applied>>(blogpost,HttpStatus.OK);
}

@RequestMapping(value="/updatejob1/{did}",method=RequestMethod.PUT)
public ResponseEntity<?>updateBlog(@PathVariable ("did")int did){
	 try{  System.out.println("------>in blogcontroller saveblog");	
		
		
	 jobdao.update(did);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}catch(Exception e){
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage("cannot insert blog post details");
		return new ResponseEntity<Job_Applied>(jobapplication,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@RequestMapping(value="/deleteJob1/{id}",method=RequestMethod.PUT)
public ResponseEntity<?>deleteBlog(@PathVariable("id")int userid ,HttpSession session){
	String users=(String)session.getAttribute("loggedInUserId");
	if(users==null){
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage("You Have Not Logged In");
		return new ResponseEntity<Job_Applied>(jobapplication,HttpStatus.UNAUTHORIZED);
		}
     try{  System.out.println("------>in blogcontroller saveblog");	
		
		
		int r=	jobdao.delete1(userid);
		if (r==0){
			jobapplication.setErrorCode("404");
			jobapplication.setErrorMessage("no record found");
			return new ResponseEntity<Job_Applied>(jobapplication,HttpStatus.OK);
		}
		jobapplication.setErrorCode("200");
		jobapplication.setErrorMessage("Recored Deleted");
		return new ResponseEntity<Job_Applied>(jobapplication,HttpStatus.OK);
	}catch(Exception e){
		jobapplication.setErrorCode("404");
		jobapplication.setErrorMessage("cannot Delete blog post details");
		return new ResponseEntity<Job_Applied>(jobapplication,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}





}
	

	
	

