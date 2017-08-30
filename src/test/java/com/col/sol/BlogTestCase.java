package com.col.sol;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.sol.dao.BlogDAO;
import com.col.sol.model.Blog;

public class BlogTestCase {
	public static void main(String[] args) 
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.col.sol");
		context.refresh();
		
		BlogDAO blogDAO=(BlogDAO)context.getBean("blogDAO");
		
		Blog blog=new Blog();
		
		/*blog.setBlogid(1001);
		blog.setBlogname("Enumeration");
		blog.setBlogcontent("Enumeration has added in jdk 1.5 version");
		blog.setUserid(1006);
		blog.setLikes(3);
		
		blogDAO.insertBlog(blog);*/
		
		//System.out.println("Blog Details Added");
		
		/*List<Blog> b=blogDAO.getAllBlogs();
		for(Blog n:b){
			System.out.println(n.getBlogcontent());
			System.out.println(n.getBlogid());
			System.out.println(n.getBlogname());
			System.out.println(n.getLikes());
			System.out.println(n.getStatus());
			System.out.println(n.getUserid());
		}*/
		
		blogDAO.delete(1003);
		/*Blog b=blogDAO.getsId(1001);
		b.setLikes(6);
		blogDAO.insertBlog(b);
			System.out.print("updated");
		*/
		
	}



}
