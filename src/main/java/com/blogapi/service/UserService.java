package com.blogapi.service;

import java.util.List;

import com.blogapi.entities.User;

public interface UserService {
	public boolean userRegister(User user);
	
	public User userLogin(User user);
	
	public List<User> getAllUsers();
	
	public boolean deleteUser(int userId);
	
	public User getUser(int userId);
}
