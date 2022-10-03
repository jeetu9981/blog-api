package com.blogapi.serviceimpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blogapi.entities.User;
import com.blogapi.exceptions.Response;
import com.blogapi.repository.UserRepo;
import com.blogapi.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired UserRepo userRepo;
	
	
	@Override
	public boolean userRegister(User user){
//		System.out.println("INSIDE : ");
//		RestTemplate restTemplate=new RestTemplate();
//		
////		String s=restTemplate.getForObject("http://localhost:9090/order/getorder?userId=2",String.class);
////		System.out.println("s : "+s);
//		
//		HttpHeaders headers = new HttpHeaders();
//	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	      HttpEntity <String> entity = new HttpEntity<String>(headers);
//	      
//	      String s=restTemplate.exchange("http://localhost:9090/order/getorder?userId=2", HttpMethod.GET, entity,String.class).getBody();
//	      System.out.println("s : "+s);
		
		if(this.userRepo.save(user)!=null)
			return true;
	
		return false;
	}

	@Override
	public User userLogin(User user) {
		user=this.userRepo.findByEmailAndPassword(user.getEmail(),user.getPassword());
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) this.userRepo.findAll();
	}

	@Override
	public boolean deleteUser(int id) {
		this.userRepo.deleteById(id);
		return true;
	}

	@Override
	public User getUser(int userId) {
		User user=this.userRepo.findById(userId).get();
		if(user!=null)
			return user;
		return null;
	}

}
