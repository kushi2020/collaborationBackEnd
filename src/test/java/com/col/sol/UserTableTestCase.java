package com.col.sol;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.sol.dao.UserTableDAO;
import com.col.sol.model.UserTable;



public class UserTableTestCase {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.col.sol");
		context.refresh();
		UserTableDAO userdao=(UserTableDAO) context.getBean("userTableDAO");
		UserTable ta=new UserTable();
		/*ta.setUserid(1003);
		ta.setEmailid("xuz@gmail.com");
		userdao.insertusertable(ta);*/
		
		List<UserTable> l1=userdao.getAllUser();
		for(UserTable p:l1){
			System.out.println(p.getEmailid());
			System.out.println(p.getUserid());
		}
		
		
		
		
	}
	

}
