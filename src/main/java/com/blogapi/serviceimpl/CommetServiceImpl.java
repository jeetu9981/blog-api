package com.blogapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.entities.Comment;
import com.blogapi.repository.CommentRepo;
import com.blogapi.service.CommentService;

@Service
public class CommetServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepo coomentRepo;

	@Override
	public boolean addComment(Comment comment1) {
		System.out.println("Comments : "+comment1.getComment());
		System.out.println("USER ID : "+comment1.getUser().getId());
		System.out.println("BLOG ID " + comment1.getBlog().getId());
		if(this.coomentRepo.save(comment1)!=null)
			return true;
		return false;
	}

}
