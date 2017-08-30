package com.col.sol.controller;

/*import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.col.sol.dao.BlogCommentDAO;
import com.col.sol.model.BlogComment;;
@RestController
public class BlogCommentController {
	@Autowired
	BlogCommentDAO blogcommentdao;
	@RequestMapping(value="/getBlogconmment/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getAll(@PathVariable("blogid")int blogid){
		List<BlogComment> listblogcomm=blogcommentdao.getAllBlogs(blogid);
		return new ResponseEntity<List<BlogComment>>(listblogcomm,HttpStatus.OK);
	
	}
	@RequestMapping(value="/insertBlogcomment",method=RequestMethod.POST)
	public ResponseEntity<String>insert2(@RequestBody BlogComment blogcomment){
		//blogcomment.setId(10076);  
		//blogcomment.setBlogId(1045);
		 // blogcomment.setComment1("everything is fine");
		  blogcomment.setCommentdate1(new Date());
		  blogcommentdao.insertBlogcomment(blogcomment);
		  return new ResponseEntity<String>("Successfully inserted",HttpStatus.OK);
		
	}
	@RequestMapping(value="/deleteblogComm/{comid}",method=RequestMethod.DELETE)
	public void deleteblogcomment(@PathVariable("comid") int id){
	    blogcommentdao.delete(id);	
	}
	@RequestMapping(value="/updateBlogcomment/{coid}",method=RequestMethod.PUT)
	public void updateblogcomment(@PathVariable("coid")int id,@RequestBody BlogComment blogcomm){
     BlogComment blogcomment=		blogcommentdao.getsId(id);
                  blogcomment.setComment1(blogcomm.getComment1());
                  blogcommentdao.insertBlogcomment(blogcomment);
	}
	
	

}*/
