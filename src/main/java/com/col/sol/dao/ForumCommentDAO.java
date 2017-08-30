package com.col.sol.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.sol.model.Forum;
import com.col.sol.model.ForumComment;

@Repository("forumCommentDAO")
@Transactional
public class ForumCommentDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	public ForumCommentDAO(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public void insertForumComment(ForumComment forumcomment){
		sessionFactory.getCurrentSession().saveOrUpdate(forumcomment);
	}
	
 public List<ForumComment>getAllForumComment(){
	 Session session= sessionFactory.openSession();
	 Query query=session.createQuery("FROM ForumComment");
	  List<ForumComment>f=query.list();
	  return f;
	 
	 
	 
 }
 
   public void delete(int id){
	   ForumComment forumcomment= (ForumComment) sessionFactory.getCurrentSession().get(ForumComment.class, id);
	   sessionFactory.getCurrentSession().delete(forumcomment);
	  	   
   }
   
   public ForumComment getForumcomment(int id){
	   ForumComment forumcomment=(ForumComment) sessionFactory.getCurrentSession().get(ForumComment.class,id);
	   return forumcomment;
	   
   }
 
	
}
