package com.col.sol;

/*import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.sol.dao.BlogCommentDAO;
import com.col.sol.model.BlogComment;

/*public class BlogCommentTestCase {
public static void main(String[] args){
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.col.sol");
	context.refresh();
	
	BlogCommentDAO blogcommentDAO=(BlogCommentDAO)context.getBean("blogCommentDAO");
	BlogComment blogcomment=new BlogComment();
	/*blogcomment.setId(102);
	blogcomment.setBlogId(2002);
	blogcomment.setComment1("good morning ");
	
	blogcommentDAO.insertBlogcomment(blogcomment);
	/*   List <BlogComment> b= blogcommentDAO.getAllBlogs();
	  for(BlogComment p:b){
		System.out.println(  p.getBlogId());
		System.out.println  (p.getComment1());
		System.out.println (p.getId());
		System.out.println( p.getUsername1());
		  
	  }
	blogcommentDAO.delete(102);
	System.out.println("delete in testcase");*/
	/*BlogComment b=blogcommentDAO.getsId(0);
	/*b.setComment1("nice");
	blogcommentDAO.insertBlogcomment(b);
	
}
}*/
