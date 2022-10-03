package com.blogapi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapi.entities.Blog;
import com.blogapi.exceptions.Response;
import com.blogapi.serviceimpl.BlogServiceImpl;

@RestController
@MultipartConfig
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogServiceImpl blogServiceImpl;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/addblog")
	public ResponseEntity<Response> addBlog(@RequestParam("image1") MultipartFile image,@ModelAttribute Blog blog){
		try 
		{
			if(this.blogServiceImpl.addBlog(image,blog.getDescription(),blog.getUser().getId(), path))
				return Response.getResponse("Blog Added Successfully...",HttpStatus.CREATED,null);
			else
				return Response.getResponse("Blog Not Added...",HttpStatus.INTERNAL_SERVER_ERROR,blog);
		} 
		catch (IOException e) 
		{
			return Response.getResponse("Blog Not Added...",HttpStatus.INTERNAL_SERVER_ERROR,blog);
		}
	}
	
	@GetMapping("/getblog")
	public ResponseEntity<Response> getBlog(@RequestParam("userId") int userId){
		List<Blog> blogs=this.blogServiceImpl.getBlogs(userId);
		if(!blogs.isEmpty())
			return Response.getResponse("Blog Found...",HttpStatus.FOUND,blogs);
		else
			return Response.getResponse("Blog Not Found...",HttpStatus.NOT_FOUND,null);
	}
	
	@DeleteMapping("deleteblog")
	public ResponseEntity<Response> deleteBlog(@RequestParam("blogId") int blogId)
	{
		if(this.blogServiceImpl.deleteBlog(blogId))
			return Response.getResponse("Blog Deleted...",HttpStatus.FOUND,null);
		else
			return Response.getResponse("Blog Not Deleted...",HttpStatus.INTERNAL_SERVER_ERROR,null);
	}
}
