package com.col.sol.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.col.sol.dao.FriendDAO;
import com.col.sol.dao.UserTableDAO;
import com.col.sol.model.Friend;
import com.col.sol.model.UserTable;
@RestController
public class FriendController {
	
	@Autowired
	Friend friend;
	@Autowired
	FriendDAO frienddao;
	@Autowired
	HttpSession httpSession;
	@Autowired
	UserTable user;
	
	@Autowired
	UserTableDAO userdao;
	
	@RequestMapping(value="/getfriend",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>>get(){
		List<Friend>j=frienddao.getAll();
		return new ResponseEntity<List<Friend>>(j,HttpStatus.OK);
		
		
	}
	@RequestMapping(value="/insertfriend",method=RequestMethod.POST)
	public void insertfriend(@RequestBody Friend friend){
		
	//	friend.setStatus("NA");
		//friend.setUserid(206);
		frienddao.insertfriend(friend);
		
	}
	
	@RequestMapping(value="/myFriends",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>>getMyFriends(){
		System.out.println("----->---->calling method getMyFriends");
		String loggedinuserID=(String) httpSession.getAttribute("loggedInUserId");
		List<Friend>myfriend=new ArrayList<Friend>();
		if(loggedinuserID==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please login to know your friends");
			myfriend.add(friend);
			return new ResponseEntity<List<Friend>>(myfriend,HttpStatus.OK);
		}
		System.out.println("getting friends of :"+ loggedinuserID);
		myfriend=frienddao.getMyFriends(loggedinuserID);
		if(myfriend.isEmpty()){
			System.out.println("Friends does not exists for the user: "+loggedinuserID);
			friend.setErrorCode("404");
			friend.setErrorMessage("You does not have any friends");
			myfriend.add(friend);
		}
		System.out.println("Send the friend list");
		return new ResponseEntity<List<Friend>>(myfriend,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value="/addFriend/{friendid1}",method=RequestMethod.GET)
	public ResponseEntity<Friend>sendFriendRequest(@PathVariable("friendid1") String friendid1){
	System.out.println("---->calling method sendFriendRequest Controller");
	String loggedinuserid=(String)httpSession.getAttribute("loggedInUserId");
	friend.setUserid1(loggedinuserid);
	friend.setFriendid1(friendid1);
	friend.setStatus("N");//N-new,R->Rejected,A->Acceped
	friend.setIs_offline('N');

	if(isUserExist(friendid1)==false)
	{
		friend.setErrorCode("404");
		friend.setErrorMessage("No user exist with the id : Controller" + friendid1);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	if(isRequestAlreadySent(friendid1)){
		friend.setErrorCode("404");
		friend.setErrorMessage("you already sent friend request to controller "+friendid1);
	}
	else{
		frienddao.save(friend);

		friend.setErrorCode("200");
		friend.setErrorMessage("Friend request successfull..controller" + friendid1);
	
		}
	return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
private boolean isUserExist(String id)
{
	if(userdao.get(id)==null)
		{System.out.println("--->in isuserexist controller");
		return false;}
	else
		 return true;
}
private boolean isRequestAlreadySent(String friendID)
{
	String loggedInUserID = (String) httpSession.getAttribute("loggedInUserId");
	
	if(frienddao.get(loggedInUserID,friendID)==null)
		return false;
	else
		return true;
}

@RequestMapping(value = "/unFriend/{friendID}", method = RequestMethod.PUT)
public ResponseEntity<Friend> unFriend(@PathVariable("friendID") String friendID1) {
	System.out.println("->->->->calling method unFriend");
	friend =updateRequest(friendID1, "U");
	return new ResponseEntity<Friend>(friend, HttpStatus.OK);

}

@RequestMapping(value = "/rejectFriend/{friendID}", method = RequestMethod.PUT)
public ResponseEntity<Friend> rejectFriendFriendRequest(@PathVariable("friendID") String friendID) {
	System.out.println("->->->->calling method rejectFriendFriendRequest");

	friend =updateRequest(friendID, "R");
	return new ResponseEntity<Friend>(friend, HttpStatus.OK);

}

@RequestMapping(value = "/accepttFriend/{friendID}", method = RequestMethod.PUT)
public ResponseEntity<Friend> acceptFriendFriendRequest(@PathVariable("friendID") String friendID) {
	System.out.println("->->->->calling method acceptFriendFriendRequest");
    
	friend = updateRequest(friendID, "A");
	return new ResponseEntity<Friend>(friend, HttpStatus.OK);

}
private Friend updateRequest(String friendID, String status) {
	System.out.println("Starting of the method updateRequest");
	String loggedInUserID = (String) httpSession.getAttribute("loggedInUserId");
	System.out.println("loggedInUserID : " + loggedInUserID);
	
	if(isRequestAlreadySent(friendID)==false)
	{
		friend.setErrorCode("404");
		friend.setErrorMessage("The request does not exist.  So you can not update to "+status);
	}
	
	if (status.equals("A") || status.equals("R"))
		friend = frienddao.get(friendID, loggedInUserID);
	else
		friend = frienddao.get(loggedInUserID, friendID);
	friend.setStatus(status); // N - New, R->Rejected, A->Accepted

	frienddao.update(friend);

	friend.setErrorCode("200");
	friend.setErrorMessage(
			"Request from   " + friend.getUserid1() + " To " + friend.getFriendid1() + " has updated to :" + status);
	System.out.println("Ending of the method updateRequest");
	return friend;

}
@RequestMapping(value = "/getMyFriendRequests/", method = RequestMethod.GET)
public ResponseEntity<List<Friend>> getMyFriendRequests() {
	System.out.println("->->->->calling method getMyFriendRequests");
	String loggedInUserID = (String) httpSession.getAttribute("loggedInUserId");
	List<Friend> myFriendRequests = frienddao.getNewFriendRequests(loggedInUserID);
	
	if(myFriendRequests.isEmpty())
	{
		friend.setErrorCode("404");
		friend.setErrorMessage("You did not received any new requests");
		myFriendRequests.add(friend);
		return new ResponseEntity<List<Friend>>(myFriendRequests, HttpStatus.OK);
		
	}

	
	
	return new ResponseEntity<List<Friend>>(myFriendRequests, HttpStatus.OK);

}
@RequestMapping("/getRequestsSendByMe")
public ResponseEntity<List<Friend>>  getRequestsSendByMe()
{
	System.out.println("->->->->calling method getRequestsSendByMe");
	
	String loggedInUserID = (String) httpSession.getAttribute("loggedInUserId");
	System.out.println("loginuser" +loggedInUserID);
	List<Friend> requestSendByMe = frienddao.getRequestsSendByMe(loggedInUserID);
	
	System.out.println("No. of request send by you : " + requestSendByMe.size());
	if(requestSendByMe.isEmpty() || requestSendByMe.size()==0)
	{
		friend.setErrorCode("404");
		friend.setErrorMessage("You did not send request to any body");
		requestSendByMe.add(friend);
		return new ResponseEntity<List<Friend>>(requestSendByMe, HttpStatus.OK);
		
	}
	
	
	System.out.println("->->->->Ending method getRequestsSendByMe");
	
	return new ResponseEntity<List<Friend>>(requestSendByMe, HttpStatus.OK);
	
}

	
@RequestMapping(value = "/listAllUsersNotFriends", method = RequestMethod.GET)
public ResponseEntity<List<UserTable>> listAllUsersNotFriends() {

	System.out.println("->->->->calling method listAllUsers");
	
	String loggedInUserID = (String) httpSession.getAttribute("loggedInUserId");
	
	System.out.println("Loggined in user id is : " + loggedInUserID);
	
	
	List<UserTable> users = frienddao.notMyFriendList(loggedInUserID);
	if (users.isEmpty()) {
		user.setErrorCode("404");
		user.setErrorMessage("No users are available");
		users.add(user);
	}

	// errorCode :200 :404
	// errorMessage :Success :Not found

	

	return new ResponseEntity<List<UserTable>>(users, HttpStatus.OK);
}



}
