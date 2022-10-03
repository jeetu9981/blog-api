package com.blogapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.entities.User;
import com.blogapi.exceptions.Response;
import com.blogapi.serviceimpl.UserServiceImpl;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired UserServiceImpl userServiceImpl;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@ModelAttribute User user){
		if(this.userServiceImpl.userRegister(user))
			return Response.getResponse("Register Succesfully....",HttpStatus.CREATED,null);
		else
			return Response.getResponse("Register Invalid....",HttpStatus.BAD_REQUEST,user);
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<Response> signin(@ModelAttribute User user){
		user=this.userServiceImpl.userLogin(user);
		if(user!=null)
			return Response.getResponse("Login Succesfully....",HttpStatus.FOUND,user);
		else
			return Response.getResponse("Login Fail....",HttpStatus.NOT_FOUND,user);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<Response> getAllUsers(){
		List<User> users=this.userServiceImpl.getAllUsers();
		if(!users.isEmpty())
			return Response.getResponse("Users Found...",HttpStatus.FOUND,users);
		else
			return Response.getResponse("No User Found....",HttpStatus.NOT_FOUND,null);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestParam("id") int userId){
		this.userServiceImpl.deleteUser(userId);
		return Response.getResponse("Delete user Successfully...",HttpStatus.OK,null);
	}
}
