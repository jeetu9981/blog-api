package com.blogapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.entities.Blog;
import com.blogapi.exceptions.Response;
import com.blogapi.serviceimpl.BlogServiceImpl;

@RestController
public class HomeController {
	@Autowired
	private BlogServiceImpl blogServiceImpl;
	
	@GetMapping("/")
	public ResponseEntity<Response> getAllBlogs(){
		List<Blog> blogs=this.blogServiceImpl.getAllBlogs();
		if(!blogs.isEmpty())
			return Response.getResponse("Blogs Found...",HttpStatus.FOUND,blogs);
		else
			return Response.getResponse("Blogs Not Found...",HttpStatus.NOT_FOUND,null);
	}
}
