package com.col.sol.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.sol.model.Forum;

@Repository("forumDAO")
public class ForumDAO {
	
@Autowired	
 SessionFactory sessionFactory;
	
	public ForumDAO(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
		System.out.println("constructor of forumdao");
	}
	@Transactional
	public void insertForum(Forum forum){
	 sessionFactory.getCurrentSession().saveOrUpdate(forum);	
	}
	@Transactional
	public List<Forum>getAllForum(){
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("FROM Forum");
		List<Forum> q= query.list();
		session.close();
		return q;
	}
	@Transactional
	public void delete(int id){
		Forum f1=(Forum) sessionFactory.getCurrentSession().get(Forum.class, id);
		sessionFactory.getCurrentSession().delete(f1);
		
		
	}
	@Transactional
	public Forum getidForum(int id){
		Forum forum=(Forum) sessionFactory.getCurrentSession().get(Forum.class,id);
		return forum;
		
	}
	
	
}
