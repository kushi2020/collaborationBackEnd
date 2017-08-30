package com.col.sol;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.col.sol.dao.FriendDAO;
import com.col.sol.model.Friend;

public class FriendTestCase {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.col.sol");
		context.refresh();
		FriendDAO frienddao=(FriendDAO) context.getBean("friendDAO");
		Friend friend=new Friend();
		/*friend.setFriendid(1001);
		friend.setStatus("NA");
		friend.setUserid(1003);
		frienddao.insertfriend(friend);*/
		List<Friend>f1=frienddao.getAll();
		for(Friend b:f1){
			System.out.println(b.getFriendid());
			System.out.println(b.getStatus());
		}
		
		
	}

}
