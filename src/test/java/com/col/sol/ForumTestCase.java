package com.col.sol;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.sol.dao.ForumDAO;
import com.col.sol.model.Forum;

public class ForumTestCase {
	public static void main(String[] args){
	   AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.col.sol");
		context.refresh();
		ForumDAO forumdao= (ForumDAO) context.getBean("forumDAO");
		Forum forum=new Forum();
		
		/*forum.setForumid(102);
		forum.setForumname("Tom");
		forum.setCreatedate(new Date());
		forum.setStatus("NA");
		forumdao.insertForum(forum);*/
		
	/*	List<Forum>b=forumdao.getAllForum();
		for(Forum f:b){
			System.out.println(f.getForumid());
			System.out.println(f.getForumname());
			System.out.println(f.getStatus());
					}*/
		//forumdao.delete(102);
		Forum form1=forumdao.getidForum(101);
		form1.setStatus("Approved");
		forumdao.insertForum(form1);
		
		
		
		
		
		
	}

}
