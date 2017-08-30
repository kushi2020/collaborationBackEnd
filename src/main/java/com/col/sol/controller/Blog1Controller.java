package com.col.sol.controller;

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

import com.col.sol.dao.Blog1Dao;
import com.col.sol.model.Blog;
import com.col.sol.model.UserTable;
import com.col.sol.model.blog1;


@RestController
public class Blog1Controller {
	@Autowired
	blog1 blogP;
    @Autowired
	private Blog1Dao blog1dao;
	@RequestMapping(value="/saveblog",method=RequestMethod.POST)
	public ResponseEntity<?>saveBlog(@RequestBody blog1 blogPost,HttpSession session){
		String users=(String)session.getAttribute("loggedInUserId");
		if(users==null){
			blogP.setErrorCode("404");
			blogP.setErrorMessage("You Have Not Logged In");
			return new ResponseEntity<blog1>(blogP,HttpStatus.UNAUTHORIZED);
			}
	     try{  System.out.println("------>in blogcontroller saveblog");	
			
			blogPost.setPostedOn(new Date());
			
			blogPost.setCreatedBy(users);
			blogPost.setApproved("N");
			blog1dao.saveBlogPost(blogPost);
			return new ResponseEntity<blog1>(blogPost,HttpStatus.OK);
		}catch(Exception e){
			blogP.setErrorCode("404");
			blogP.setErrorMessage("cannot insert blog post details");
			return new ResponseEntity<blog1>(blogP,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/listofBlogs",method=RequestMethod.GET)
	public ResponseEntity<?>getAllBlogs(HttpSession session){
		String users=(String)session.getAttribute("loggedInUserId");
		if(users==null){
			blogP.setErrorCode("404");
			blogP.setErrorMessage("UnAuthorized user");
			return new ResponseEntity<blog1>(blogP,HttpStatus.UNAUTHORIZED);
		}
		List<blog1>blogpost=blog1dao.getAllBlogs();
		return new ResponseEntity<List<blog1>>(blogpost,HttpStatus.OK);
	}
	@RequestMapping(value="/update/{did}",method=RequestMethod.PUT)
	public ResponseEntity<?>updateBlog(@PathVariable ("did")int did){
		 try{  System.out.println("------>in blogcontroller saveblog");	
			
			
			blog1dao.update(did);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e){
			blogP.setErrorCode("404");
			blogP.setErrorMessage("cannot insert blog post details");
			return new ResponseEntity<blog1>(blogP,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value="/deleteBlog/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?>deleteBlog(@PathVariable("id")int userid ,HttpSession session){
		String users=(String)session.getAttribute("loggedInUserId");
		if(users==null){
			blogP.setErrorCode("404");
			blogP.setErrorMessage("You Have Not Logged In");
			return new ResponseEntity<blog1>(blogP,HttpStatus.UNAUTHORIZED);
			}
	     try{  System.out.println("------>in blogcontroller saveblog");	
			
			
			int r=	blog1dao.delete(userid);
			if (r==0){
				blogP.setErrorCode("404");
				blogP.setErrorMessage("no record found");
				return new ResponseEntity<blog1>(blogP,HttpStatus.OK);
			}
			blogP.setErrorCode("200");
			blogP.setErrorMessage("Recored Deleted");
			return new ResponseEntity<blog1>(blogP,HttpStatus.OK);
		}catch(Exception e){
			blogP.setErrorCode("404");
			blogP.setErrorMessage("cannot Delete blog post details");
			return new ResponseEntity<blog1>(blogP,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/getBlog",method=RequestMethod.GET)
	public ResponseEntity<List<blog1>> getAllBlogs1()
	{
		List<blog1> listblogs=blog1dao.getAllBlogs1();
		
		return new ResponseEntity<List<blog1>>(listblogs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updatelike",method=RequestMethod.PUT)
	public ResponseEntity<?>updateBloglike(@RequestBody blog1 blog){
		 try{  System.out.println("------>in blogcontroller saveblog");	
			
			
			blog1dao.updatelike(blog);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e){
			blogP.setErrorCode("404");
			blogP.setErrorMessage("cannot insert blog post details");
			return new ResponseEntity<blog1>(blogP,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/getBloglike",method=RequestMethod.GET)
	public ResponseEntity<List<blog1>> getAllBlogs1like()
	{
		List<blog1> listblogs=blog1dao.getAllBlogs1like();
		
		return new ResponseEntity<List<blog1>>(listblogs,HttpStatus.OK);
	}
	
	}
