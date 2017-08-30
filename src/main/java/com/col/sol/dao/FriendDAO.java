package com.col.sol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.col.sol.model.Friend;
import com.col.sol.model.UserTable;
import com.col.sol.model.blog1;

@Repository("friendDAO")
@Transactional
public class FriendDAO {
	@Autowired
	SessionFactory sessionFactory;
	public FriendDAO(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public void insertfriend(Friend friend){
		sessionFactory.getCurrentSession().saveOrUpdate(friend);
		
	}
	public List<Friend> getAll(){
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("FROM Friend");
		List<Friend>l1=query.list();
		return l1;
	}
	
	@Transactional
	public void setOnline(String friendID) {
		System.out.println("Starting of the metnod setOnline");
		//String hql = " UPDATE Friend	SET isOnline = 'Y' where friendID='" + friendID + "'";
		
		String hql = " UPDATE Friend	SET online = 'Y' where friendID1= ?";
		
		
		
		
		System.out.println("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.setString(0, friendID);
		
		query.executeUpdate();
		System.out.println("Ending of the metnod setOnline");

	}
	@Transactional
	public void setOffLine(String friendID) {
		System.out.println("Starting of the metnod setOffLine");
		String hql = " UPDATE Friend	SET Online = 'N' where friendID1='" + friendID + "'";
		System.out.println("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();
		System.out.println("Ending of the metnod setOffLine");

	}
	
	public boolean update(Friend friend){
		try{
			System.out.println("Starting of method update");
			System.out.println("user Id: "+friend.getUserid1()+"friend id"+friend.getFriendid1());
			sessionFactory.getCurrentSession().update(friend);
			System.out.println("successfully updated");
			return true;
			
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

public void delete(String userid,String friendid){
	Friend friend=new Friend();
	friend.setFriendid1(friendid);
    friend.setUserid1(userid);
sessionFactory.openSession().delete(friend);
}

public List<Friend>getMyFriends(String userid){
	
	String hql="select friendid1 from Friend where userid1='"+ userid+"'and status='A'";

	
	String hq2="select userid1 from Friend where friendid1='"+ userid+"'and status='A'";
   System.out.println("getMyfriends hql1: "+hql);
   System.out.println("getMyFriend hq2: "+hq2);
   List<Friend>list1=sessionFactory.openSession().createQuery(hql).list();
   List<Friend>list2=sessionFactory.openSession().createQuery(hq2).list();
   list1.addAll(list2);
   return list1;

}
public List<Friend>getNewFriendRequests(String friendid){
	String hql = "select userid1 from Friend where friendid1=" + "'" + friendid + "' and status ='" + "N'";
	 return  sessionFactory.openSession().createQuery(hql).list();
}
public List<Friend> getRequestsSendByMe(String userID) {
	String hql = "select friendid1 from Friend where userid1=" + "'" + userID + "' and status ='" + "N'";

	
	return  sessionFactory.openSession().createQuery(hql).list();

}
public Friend get(String userID, String friendID) {
	String hql = "from Friend where userid1="+"'"+userID+"'and friendid1='"+friendID+"'";

	System.out.println("hql: frienddao " + hql);
	Query query = sessionFactory.openSession().createQuery(hql);
      System.out.println("frienddao:---->"
      		+ query);
	return (Friend) query.uniqueResult();

}

@Transactional
public boolean save(Friend friend) {

	try {
		System.out.println("*********************88Previous id "+friend.getFriendid1() );
		System.out.println("***********************generated id:" );
		sessionFactory.getCurrentSession().save(friend);
		return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}

}

/*public List<UserTable> notMyFriendList(String userID) {
	String hql = "from UserTable where firstname not in( SELECT friendid1 from"
			+ " Friend  where userid1 ='"+userID +""
			+ "' OR friendid1= '" + userID+ "')";
	System.out.println("Query:"+ hql);
	return sessionFactory.getCurrentSession().createQuery(hql).list();
}*/

public List<UserTable>notMyFriendList(String userId){
	Session session=sessionFactory.openSession();
	
	SQLQuery query=session.createSQLQuery("select * from UserTable where firstname in "
	+"(select firstname from UserTable where firstname!=? "
	+"minus "
	+"(select friendid1 from Friend where userid1=?"
	+" union "
	+"select userid1 from Friend where friendid1=?))");
query.setParameter(0,userId);
query.setParameter(1,userId);
query.setParameter(2,userId);
query.addEntity(UserTable.class);
List<UserTable> blogs=query.list();
session.close();
return blogs;

}

}
