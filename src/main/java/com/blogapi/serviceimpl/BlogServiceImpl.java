package com.blogapi.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapi.entities.Blog;
import com.blogapi.entities.User;
import com.blogapi.repository.BlogRepo;
import com.blogapi.repository.UserRepo;
import com.blogapi.service.BlogService;


@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Value("${project.image}")
	private String path;

	@Override
	public boolean addBlog(MultipartFile image, String desc, int userId,String path) throws IOException {
		String name=image.getOriginalFilename();
		String fullPath=path+File.separator+name;
		File file=new File(path);
		if(!file.exists())
			file.mkdir();
		Files.copy(image.getInputStream(),Paths.get(fullPath));
		
		User user=new User();
		user.setId(userId);
		
		Blog blog=new Blog();
		blog.setDescription(desc);
		blog.setImage(name);
		blog.setUser(user);
		
		if(this.blogRepo.save(blog)!=null)
			return true;
		return false;
	}

	@Override
	public List<Blog> getBlogs(int userId) {
		User user=this.userRepo.findById(userId).get();
		return user.getBlogs();
	}

	@Override
	public boolean deleteBlog(int blogId) {
		Blog blog=this.blogRepo.findById(blogId).get();
		File file=new File(path+blog.getImage());
		if(file.delete()) {
			this.blogRepo.delete(blog);
			return true;
		}
		return false;
	}

	@Override
	public List<Blog> getAllBlogs() {
		return (List<Blog>) this.blogRepo.findAll();
	}

}
