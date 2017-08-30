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

import com.col.sol.dao.ForumDAO;
import com.col.sol.model.BlogComment;
import com.col.sol.model.Forum;
@RestController
public class ForumController {
	@Autowired
	ForumDAO forumdao;
	@RequestMapping(value="/getforum",method=RequestMethod.GET)
	public ResponseEntity<List<Forum>>getforum(){
		    List<Forum> f =forumdao.getAllForum();
		
		return new ResponseEntity<List<Forum>>(f,HttpStatus.OK);
		
	}
	@RequestMapping(value="/insertforum",method=RequestMethod.POST)
	public void insertforum(@RequestBody Forum forum){
		forum.setCreatedate(new Date());
		forum.setStatus("nA");
		forumdao.insertForum(forum);
		}
	
	@RequestMapping(value="/deleteform/{fid}",method=RequestMethod.DELETE)
	public void deleteform(@PathVariable("fid") int fi){
		forumdao.delete(fi);
	
	}
	
	@RequestMapping(value="/updateforum/{coid}",method=RequestMethod.PUT)
	public void updateblogcomment(@PathVariable("coid")int id,@RequestBody Forum forum){
     Forum f=		forumdao.getidForum(id);
                  f.setForumname(forum.getForumname());
                  forumdao.insertForum(f);
	}
	
	

}
