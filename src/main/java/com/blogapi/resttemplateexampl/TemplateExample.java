package com.blogapi.resttemplateexampl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.blogapi.entities.User;
import com.blogapi.exceptions.Response;

public class TemplateExample {
//	@Autowired
//	private static RestTemplate restTemplate;
	
	private static final String CREATE_USER="http://localhost:8080/user/register";
	
	public static void main(String[] args) {
		getBlog(2);
	}
	
	
	public static ResponseEntity<Response> getBlog(int userId) {
		RestTemplate restTemplate=new RestTemplate();
		User user=new User();
		user.setName("neha");
		user.setEmail("neha@gmail.com");
		user.setPassword("1478");
		
		HttpEntity<User> entity=new HttpEntity<User>(user);
		
		User s = restTemplate.exchange(CREATE_USER,HttpMethod.POST,entity,User.class).getBody();
		return Response.getResponse("Success...",HttpStatus.FOUND,s);
	}
}
