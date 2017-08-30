package com.col.sol.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.sol.model.BlogComment;

/*@Repository("blogCommentDAO")
public class BlogCommentDAO {
	@Autowired
	SessionFactory sessionFactory;
	public BlogCommentDAO(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		System.out.println("Constructor of Blog");
	}
	@Transactional
	public void insertBlogcomment(com.col.sol.model.BlogComment blogcomment)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(blogcomment);
	}
	/*@Transactional
	public List<BlogComment> getAllBlogs(){
		Session session=sessionFactory.openSession();
		
		Query query=session.createQuery("From BlogComment");
		List<BlogComment> blogs=query.list();
		session.close();
		return blogs;
	}
	*/
	/*@Transactional
	public List<BlogComment> getAllBlogs(int blogid){
		Session session=sessionFactory.openSession();
		
		Query query=session.createQuery("From BlogComment where blogid=:blgid");
		query.setParameter("blgid", new Integer(blogid));
		@SuppressWarnings("unchecked")
		List<BlogComment> blogs=query.list();
		session.close();
		return blogs;
	}
	@Transactional
	public void delete(int id){
	BlogComment b=	(BlogComment) sessionFactory.getCurrentSession().get(BlogComment.class, id);
	
	sessionFactory.getCurrentSession().delete(b);	
		
	}

	@Transactional
	public BlogComment getsId(int id){
		Session session=sessionFactory.openSession();
		BlogComment blogcomment=(BlogComment) sessionFactory.getCurrentSession().get(BlogComment.class,id);
	    session.close();
	return blogcomment;
	}
	

}
*/