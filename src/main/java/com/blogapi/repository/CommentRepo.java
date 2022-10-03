package com.blogapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.blogapi.entities.Comment;

public interface CommentRepo extends CrudRepository<Comment,Integer>{

}
