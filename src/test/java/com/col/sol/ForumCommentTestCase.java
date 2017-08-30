package com.col.sol;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.sol.dao.ForumCommentDAO;
import com.col.sol.model.ForumComment;

public class ForumCommentTestCase {
	public static void main(String[] args){
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
     context.scan("com.col.sol");
     context.refresh();
     
       ForumCommentDAO forumcommentdao=(ForumCommentDAO) context.getBean("forumCommentDAO");
       
       ForumComment forumcomment=new ForumComment();
     /*  
       forumcomment.setId(1004);
       forumcomment.setComment1("good");
       forumcomment.setUserid(4006);
       forumcommentdao.insertForumComment(forumcomment);
       
      /* List<ForumComment>f=forumcommentdao.getAllForumComment();
       for(ForumComment f1:f){
    	   System.out.println(f1.getId());
    	   System.out.println(f1.getUserid());
    	   System.out.println(f1.getUsername());
    	   System.out.println(f1.getComment1());
       }*/
       
       //forumcommentdao.delete(1002);
      ForumComment forumcomment1= forumcommentdao.getForumcomment(1004);
      forumcomment1.setCommentdate(new Date());
      forumcommentdao.insertForumComment(forumcomment1);
       
       
       
       
     
}
}
