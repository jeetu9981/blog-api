package com.blogapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.blogapi.entities.Blog;

public interface BlogRepo extends CrudRepository<Blog,Integer>{

}
