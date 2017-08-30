package com.col.sol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.sol.model.UserTable;


@Repository("userTableDAO")
@Transactional
public class UserTableDAO {
	
@Autowired
SessionFactory sessionFactory;

public UserTableDAO(SessionFactory sessionFactory){
	this.sessionFactory=sessionFactory;
}

public boolean insertusertable(UserTable userta){

System.out.println("->->Starting of the method save");
try {
	sessionFactory.getCurrentSession().saveOrUpdate(userta );
	System.out.println("->->User is created successfully");
	return true;
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	return false;
}
}


public List<UserTable>getAllUser(){
	Session session=sessionFactory.openSession();
	Query query=session.createQuery("FROM UserTable");
	   List<UserTable> k=query.list();
	   session.close();
	   return k;
	
	
}
/*
public UserTable authenticate(String firstname, String possword) {

	 return (UserTable)sessionFactory.getCurrentSession().createCriteria(UserTable.class)  
			.add(Restrictions.eq("firstname", firstname))
			.add(Restrictions.eq("possword", possword))
			
			.uniqueResult();

		
}
*/
public UserTable authenticate(String firstname, String possword) {

Session session=sessionFactory.openSession();
	
	SQLQuery query=session.createSQLQuery("select * from usertable where firstname=? and possword=? and status='A'");
query.setParameter(0,firstname);
query.setParameter(1,possword);

query.addEntity(UserTable.class);
UserTable blogs=(UserTable) query.uniqueResult();
session.close();
return blogs;
		
}
/*public List<UserTable> getallnotapproved() {

Session session=sessionFactory.openSession();
	
	SQLQuery query=session.createSQLQuery("select * from UserTable where status=?");
	query.setParameter(0,'N');

List<UserTable> blogs=query.list();
session.close();
return blogs;
		
}*/
public List<UserTable> getallnotapproved() {

Session session=sessionFactory.openSession();
	
	//SQLQuery query=session.createSQLQuery("select * from UserTable where status=?");
	//query.setParameter(0,'N');
String hq="from UserTable where status='N'";
 Query query=sessionFactory.getCurrentSession().createQuery(hq);
List<UserTable> blogs=query.list();
session.close();
return blogs;
		
}



public UserTable get(String firstname) {
	System.out.println("->->Starting of the method get");
	System.out.println("->->id : " + firstname);
	String hql = "from UserTable where firstname=" + "'"+ firstname + "'" ;
	 return getUser(hql);
}

private UserTable getUser(String hql)
{
System.out.println("->->Starting of the method getUser");
System.out.println("->->hql : " +hql);
    Query query = sessionFactory.getCurrentSession().createQuery(hql);
    System.out.println("----> end of getuser");
	@SuppressWarnings("unchecked")
	List<UserTable> list = (List<UserTable>) query.list();
	
	if (list != null && !list.isEmpty()) {
		System.out.println("----->list.get(0)--->" +list.get(0));
		return list.get(0);
	}
	return null;
}

public boolean update(UserTable user) {
	
		System.out.println("->->Starting of the method update");
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

public boolean update1(int userid){

	try{
		String hql =" UPDATE UserTable	SET status = 'A' where userid='" +  userid + "'" ;
		System.out.println("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		System.out.println("Ending of the metnod update");
return true;
	}catch(HibernateException e){

return false ;
	}

}


public void setOffLine(String firstname) {
	System.out.println("Starting of the metnod setOffLine");
	String hql =" UPDATE usertable	SET isOnline = 'N' where firstname='" +  firstname + "'" ;
	System.out.println("hql: " + hql);
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	query.executeUpdate();
	System.out.println("Ending of the metnod setOffLine");
	
	
}
	
}

	




	







	


