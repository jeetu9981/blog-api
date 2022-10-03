package com.blogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.entities.Comment;
import com.blogapi.exceptions.Response;
import com.blogapi.serviceimpl.CommetServiceImpl;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommetServiceImpl comentServiceImpl;
	
	@PostMapping("/addcomment")
	public ResponseEntity<Response> addComment(@ModelAttribute Comment comment1,@RequestParam("com") String com){
		if(this.comentServiceImpl.addComment(comment1))
			return Response.getResponse("Comment Added...",HttpStatus.CREATED,null);
		else
			return Response.getResponse("Comment Not Added...",HttpStatus.INTERNAL_SERVER_ERROR,null);
	}
}
