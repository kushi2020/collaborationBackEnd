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
@Repository("blogDAO")
public class BlogDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	public BlogDAO(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		System.out.println("Constructor of Blog");
	}
	
	@Transactional
	public void insertBlog(com.col.sol.model.Blog blog)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
	}
	@Transactional
	public List<Blog> getAllBlogs(){
		Session session=sessionFactory.openSession();
		
		Query query=session.createQuery("From blog1");
		List<Blog> blogs=query.list();
		session.close();
		return blogs;
	}
@Transactional
public void delete(int id){
		
		// TODO Auto-generated method stub
Blog b=(Blog) sessionFactory.getCurrentSession().get(Blog.class, id);		
     sessionFactory.getCurrentSession().delete(b);
	

}
@Transactional
public Blog getsId(int id){
	Session session=sessionFactory.openSession();
	Blog blog=(Blog) sessionFactory.getCurrentSession().get(Blog.class,id);
session.close();
return blog;
}


}
