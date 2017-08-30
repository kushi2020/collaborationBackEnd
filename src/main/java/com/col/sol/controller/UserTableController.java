package com.col.sol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.col.sol.dao.UserTableDAO;
import com.col.sol.model.Job;
import com.col.sol.model.UserTable;
@RestController
public class UserTableController {
	@Autowired
	UserTableDAO usertabledao;
	@Autowired
	UserTable user;
	
	@Autowired
	HttpSession session;
	@PostMapping("/login")
	public ResponseEntity<UserTable>login(@RequestBody UserTable user){
		user=usertabledao.authenticate(user.getFirstname(),user.getPossword());
		System.out.println("restcontroller------>"+user);
		if(user==null){
			user=new UserTable();
			user.setErrorCode("404");
			
			user.setErrorMessage("Please Enter Valid Credentials");
			System.out.println("in if rest controller"+ user.getErrorCode()+user.getErrorMessage());
		}
		else
			{user.setErrorCode("200");
		user.setErrorMessage("you have successfully logged in");
		user.setIsonline("y");
		System.out.println("in rest else"+user.getErrorCode()+user.getErrorMessage());
		session.setAttribute("loggedInUserId",user.getFirstname());
		session.setAttribute("loggedInUserROle",user.getRole());
			}
		return new ResponseEntity<UserTable>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getusertable",method=RequestMethod.GET)
	public ResponseEntity<List<UserTable>>getusertable(){
		        List<UserTable> k=usertabledao.getAllUser();
		  
		return new ResponseEntity<List<UserTable>>(k,HttpStatus.OK);
		
	}
	//admin will get all records which are not approved
	@RequestMapping(value="/getusersnotapproved",method=RequestMethod.GET)
	public ResponseEntity <List<UserTable>>getusersnotapproved(){
		        List<UserTable> k=usertabledao.getallnotapproved();
		  
		return new ResponseEntity<List<UserTable>>(k,HttpStatus.OK);
		
	}
	
	
	
	
	/*@RequestMapping(value="/insertusertable",method=RequestMethod.POST)
	public void insertusertable(@RequestBody UserTable usertable){
		//usertable.setUserid(188);
	//	usertable.setEmailid("k@gmail.com");
	//	usertable.setFirstname("paul");
	//	usertable.setIsonline("no");
	//	usertable.setLastname("david");
	//	usertable.setPossword("123");
	//	usertable.setRole("MALE");
	//	usertable.setStatus("NA");
		usertabledao.insertusertable(usertable);
	  
	}*/
	
	@RequestMapping(value="/insertusertable",method=RequestMethod.POST)
	public ResponseEntity<UserTable> insertusertable(@RequestBody UserTable usertable){
		
		System.out.println("->->->->calling method createUser");
		if (usertabledao.get(usertable.getFirstname()) == null) {
			System.out.println("->->->->User is going to create with id:" + usertable.getFirstname());
			usertable.setIsonline("N");
			usertable.setStatus("N");
			  if (usertabledao.insertusertable(usertable)==true)
			  {
				  usertable.setErrorCode("200");
					usertable.setErrorMessage("Thank you  for registration. You have successfully registered as " + usertable.getRole());
			  }
			  else
			  {
				  usertable.setErrorCode("404");
					usertable.setErrorMessage("Could not complete the operatin please contact Admin");
		
				  
			  }
			return new ResponseEntity<UserTable>(usertable,HttpStatus.OK);	
		
		//usertable.setUserid(188);
	//	usertable.setEmailid("k@gmail.com");
	//	usertable.setFirstname("paul");
	//	usertable.setIsonline("no");
	//	usertable.setLastname("david");
	//	usertable.setPossword("123");
	//	usertable.setRole("MALE");
	//	usertable.setStatus("NA");
		
	  
	}
		System.out.println("->->->->User already exist with id " + usertable.getFirstname());
		usertable.setErrorCode("404");
		usertable.setErrorMessage("User already exist with id : " + usertable.getFirstname());
 return new ResponseEntity<UserTable>(usertable,HttpStatus.OK);
	}
	//admin will update the permission to enter the site
	@RequestMapping(value="/adminupdate/{did}",method=RequestMethod.POST)
	public ResponseEntity<UserTable> adminupdate(@PathVariable("did")int did){
		
		System.out.println("->->->->calling method createUser");
	boolean bu=	usertabledao.update1(did);
					  if(bu){
						  user.setErrorCode("200");
						  user.setErrorMessage("successfully Updated");
					  }
			  
 return new ResponseEntity<UserTable>(user,HttpStatus.OK);
	}

	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<UserTable> logout(HttpSession session) {
		System.out.println("->->->->calling method logout");
		//String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		
		 //user = usertabledao.get(loggedInUserID);
		 
		 
	//	 user.setLastSeenTime(new Date(  System.currentTimeMillis()));
		// usertabledao.update(user);
		 
		
		//friendDAO.setOffLine(loggedInUserID);
		//usertabledao.setOffLine(loggedInUserID);

		session.invalidate();

		user.setErrorCode("200");
		user.setErrorMessage("You have successfully logged");
		return new ResponseEntity<UserTable>(user, HttpStatus.OK);
	};
	
	

}
