  package com.col.sol.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.sol.model.Job;
import com.col.sol.model.Job_Applied;
import com.col.sol.model.UserTable;
import com.col.sol.model.blog1;

@Repository("jobDAO")
@Transactional
public class JobDAO {
		
	@Autowired
	SessionFactory sessionFactory;
	public JobDAO(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
		public boolean insertjob(Job job){
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(job);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
			
		}
		public boolean save(Job job){
			try{
				sessionFactory.getCurrentSession().save(job);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
				
			}
		
	public List<Job>getAllJob(){
		 Session session= sessionFactory.openSession();
		        Query query= session.createQuery("FROM Job");
		       List<Job> j =query.list();
		       session.close();
		 
		return j;
		
	}
	
	public void delete(int id){
		Job job=(Job) sessionFactory.getCurrentSession().get(Job.class, id);
		sessionFactory.getCurrentSession().delete(job);
	}
	
	public Job getSingleJob(int id){
		Job j=(Job) sessionFactory.getCurrentSession().get(Job.class, id);
		return j;
	}

public boolean update(Job job){
	try{
		sessionFactory.getCurrentSession().update(job);
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
	return true;

}
public List<Job_Applied>getAllJobsStatusN(){
	Session session=sessionFactory.openSession();
	Query query=session.createQuery("from Job_Applied where status= :code");
	query.setParameter("code","N");
	List<Job_Applied>blogp=query.list();
	session.close();
	return blogp;
		
}

public List<Job>getAllApplied(String userid){
	Session session=sessionFactory.openSession();
		
		SQLQuery query=session.createSQLQuery("select * from Job where jobid in "
		+"(select job_id from Job_Applied where userid=?)");
		
	query.setParameter(0,userid);
	query.addEntity(Job.class);
	List<Job> blogs=query.list();
	session.close();
	return blogs;

	}

	
	


public boolean save(Job_Applied jobapplication){
	try{
		sessionFactory.getCurrentSession().save(jobapplication);
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
	return true;

}
	
public boolean update(Job_Applied jobapplication){
	try{
		sessionFactory.getCurrentSession().saveOrUpdate(jobapplication);
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
	return true;

}

public List<Job> list(String status)
{
	return sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("status", status)).list();
	 
}
		
public List<Job_Applied>list1(String firstname){
	return sessionFactory.getCurrentSession().createCriteria(Job_Applied.class).add(Restrictions.eqOrIsNull("firstname",firstname)).list();
}

public Job_Applied getjobapplication(String userid,int jobid){
	String hql="from Job_Applied where userid='"+userid+"' and job_id='"+jobid+"'";
	return (Job_Applied)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
}

public List<Job>getAllOpendJobs(){
	return sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eqProperty("status","v")).list();
}
public List<Job> getMyAppliedJobs(String userID) {
	System.out.println("Starting of method getMyAppliedJobs");
	String hql = "from Job_Applied where userid ='"+ userID +"'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	System.out.println("Ending of method getMyAppliedJobs");
	return query.list();
	
}
public void update(int id){
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	Query query=session.createQuery("update Job_Applied set status= :code where id= :userid");
	 query.setParameter("code","C");
	 query.setParameter("userid", id);
	 query.executeUpdate();
	 tx.commit();
	 session.close();
}

public int delete1(int userid){
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	Query query=session.createQuery("delete from Job_Applied where  status= :code and id= :userid");
	 query.setParameter("code","N");
	 query.setParameter("userid", userid);
	int r= query.executeUpdate();
	System.out.println("r-------->"+r);
	 tx.commit();
	 session.close();
	 return r;
}
}
