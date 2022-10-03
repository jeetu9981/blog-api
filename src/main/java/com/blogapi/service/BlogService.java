package com.blogapi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blogapi.entities.Blog;

public interface BlogService {
	public boolean addBlog(MultipartFile image,String desc,int userId,String path) throws IOException;
	public List<Blog> getBlogs(int userId);
	public boolean deleteBlog(int blogId);
	public List<Blog> getAllBlogs();
}
