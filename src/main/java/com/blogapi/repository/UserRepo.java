package com.blogapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.blogapi.entities.User;



public interface UserRepo extends CrudRepository<User,Integer>{
	public User findByEmailAndPassword(String email,String password);
}
