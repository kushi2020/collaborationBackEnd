package com.col.sol.conf;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.col.sol.dao.Blog1Dao;
//import com.col.sol.dao.BlogCommentDAO;
import com.col.sol.dao.BlogDAO;
import com.col.sol.dao.ForumCommentDAO;
import com.col.sol.dao.ForumDAO;
import com.col.sol.dao.FriendDAO;
import com.col.sol.dao.JobDAO;
import com.col.sol.dao.UserTableDAO;
import com.col.sol.model.Blog;
import com.col.sol.model.BlogComment;
import com.col.sol.model.Forum;
import com.col.sol.model.ForumComment;
import com.col.sol.model.Friend;
import com.col.sol.model.Job;
import com.col.sol.model.Job_Applied;
import com.col.sol.model.UserTable;
import com.col.sol.model.blog1;
@Configuration
@EnableTransactionManagement

public class Appconf {

	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		//Hibernate related properties
		Properties hibernateprop=new Properties();
		hibernateprop.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateprop.put("hibernate.show_sql", "true"); //optional
		hibernateprop.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		
		//Adding the Database related Properties
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("hr2");
		dataSource.setPassword("hr2");
		
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(hibernateprop);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(ForumComment.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(UserTable.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Job_Applied.class);
		sessionBuilder.addAnnotatedClass(blog1.class);
		SessionFactory sessionfactory=sessionBuilder.buildSessionFactory();
		
		System.out.println("Session Factory Object Created");
		
		return sessionfactory;
		
	}

/*	@Bean(name="blogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory)
	{
		return new BlogDAO(sessionFactory);
	}
	
	/*@Bean(name="blogCommentDAO")
	public BlogCommentDAO getBlogCommentDAO(SessionFactory sessionFactory)
	{
		return new BlogCommentDAO(sessionFactory);
	}*/
	
	@Bean(name="forumDAO")
	public ForumDAO getBlogForumtDAO(SessionFactory sessionFactory)
	{
		return new ForumDAO(sessionFactory);
	}
	@Bean(name="forumCommentDAO")
	public ForumCommentDAO getForumtCommentDAO(SessionFactory sessionFactory)
	{
		return new ForumCommentDAO(sessionFactory);
	}
	@Bean(name="jobDAO")
	public JobDAO getjobDAO(SessionFactory sessionFactory)
	{
		return new JobDAO(sessionFactory);
	}
	@Bean(name="userTableDAO")
	public UserTableDAO getUserTableDAO(SessionFactory sessionFactory)
	{
		return new UserTableDAO(sessionFactory);
	}
	@Bean(name="friendDAO")
	public FriendDAO getfriendDAO(SessionFactory sessionFactory)
	{
		return new FriendDAO(sessionFactory);
	}
	@Bean(name="blog1DAO")
	public Blog1Dao getBlog1DAO(SessionFactory sessionFactory)
	{
		return new Blog1Dao(sessionFactory);
	}
	
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("Transaction Manager");
		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	
}
