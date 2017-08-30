package com.col.sol.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.col.sol.dao.ForumCommentDAO;
import com.col.sol.model.ForumComment;

@RestController
public class ForumCommentController {
	@Autowired
	ForumCommentDAO forumcommentdao;
	@RequestMapping(value="/getforumcomment",method=RequestMethod.GET)
	public ResponseEntity<List<ForumComment>> getforumcomment(){
		
        List<ForumComment> k=forumcommentdao.getAllForumComment();
        return new ResponseEntity<List<ForumComment>>(k,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/insertforumcomment",method=RequestMethod.POST)
	public void insertforumcomment(@RequestBody ForumComment forumcomment){
		forumcomment.setId(104);
		forumcomment.setComment1("nice pic");
		forumcomment.setCommentdate(new Date());
		forumcomment.setUserid(106);
		forumcomment.setUsername("Oman");
		forumcommentdao.insertForumComment(forumcomment);
		
	}
	
	@RequestMapping(value="/deleteforumcomment/{sid}",method=RequestMethod.DELETE)
	public void deleteforumcomment(@PathVariable("sid") int sid)
	{
                     forumcommentdao.delete(sid); 		
	}
	
	@RequestMapping(value="/updateforumcomment/{fid}",method=RequestMethod.PUT)
	public void updateforumcomment(@PathVariable("fid")int fid,@RequestBody ForumComment forumcomment){
		ForumComment fo=forumcommentdao.getForumcomment(fid);
		fo.setUsername(forumcomment.getUsername());
		forumcommentdao.insertForumComment(fo);
		
		
		
	}

}
