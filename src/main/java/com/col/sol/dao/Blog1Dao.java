package com.col.sol.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.sol.model.Blog;
import com.col.sol.model.blog1;

@Repository("blog1DAO")
public class Blog1Dao {
	@Autowired
	SessionFactory sessionFactory;
	public Blog1Dao(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		System.out.println("Constructor of Blog");
	}

@Transactional
	public void saveBlogPost(blog1 blog1){
	System.out.println("------->saveblogpost dao"+blog1.getDescription());
	 Session session=sessionFactory.openSession();
	 Transaction tx=session.beginTransaction();
		session.save(blog1);
		tx.commit();
		session.close();
	}
	
	public List<blog1>getAllBlogs(){
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from blog1 where approved= :code");
		query.setParameter("code","N");
		List<blog1>blogp=query.list();
		session.close();
		return blogp;
			
	}
	public void update(int id){
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Query query=session.createQuery("update blog1 set approved= :code where id= :userid");
		 query.setParameter("code","A");
		 query.setParameter("userid", id);
		 query.executeUpdate();
		 tx.commit();
		 session.close();
	}
	
	public int delete(int userid){
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Query query=session.createQuery("delete from blog1 where  approved= :code and id= :userid");
		 query.setParameter("code","N");
		 query.setParameter("userid", userid);
		int r= query.executeUpdate();
		System.out.println("r-------->"+r);
		 tx.commit();
		 session.close();
		 return r;
	}
	@Transactional
	public List<blog1> getAllBlogs1(){
		Session session=sessionFactory.openSession();
		
		Query query=session.createQuery("From blog1");
		List<blog1> blogs=query.list();
		session.close();
		return blogs;
	}
	public void updatelike(blog1 blog){
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Query query=session.createQuery("update blog1 set likes= :code,dislike=:dislike where id= :userid and approved=:appr");
		 query.setParameter("code",blog.getLikes());
		 query.setParameter("dislike", blog.getDislike());
		 query.setParameter("userid", blog.getId());
		 query.setParameter("appr", "A");
		 query.executeUpdate();
		 tx.commit();
		 session.close();
	}
	@Transactional
	public List<blog1> getAllBlogs1like(){
		Session session=sessionFactory.openSession();
		
		Query query=session.createQuery("From blog1 where approved=:code");
		query.setParameter("code", "A");
		List<blog1> blogs=query.list();
		session.close();
		return blogs;
	}
	
	
}
