package com.blogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.blogapi.resttemplateexampl.TemplateExample;

@SpringBootApplication
public class BlogapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogapiApplication.class, args);
	}
	
//	@Bean
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}

}
