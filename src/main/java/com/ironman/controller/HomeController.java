package com.ironman.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironman.entity.User;
import com.ironman.serviceInter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZJW
 * @Description:
 * @Date: Created in 9:52 2017/11/20 0020
 **/
@RestController
public class HomeController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/user/{id}")
	public User index(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}

	@RequestMapping(value = "/user")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public List<User> insertUser(@RequestBody User user){
		userService.insertUser(user);
		System.out.println(user.toString());
		//此处无法查到刚刚插入的user
		return userService.getAllUser();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public List<User> deleteUser(@PathVariable("id") Long id){
		userService.deleteUserById(id);
		return userService.getAllUser();
	}

	@RequestMapping(value = "/user/verify", method = RequestMethod.POST)
	public User verify(@RequestBody Map<String, String> paramMap){
		String username = paramMap.get("username");
		String psw = paramMap.get("psw");
		System.out.println(username + "\t" + psw);
		return userService.getUserByUsernameAndPassword(username, psw);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public User UpdateUserName(@PathVariable("id") Long id, @RequestBody String body) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(body);
		String userName = node.get("username").asText();
		System.out.println(userName);
		userService.UpdateUserName(userName, id);
		return userService.getUserById(id);
	}


	@RequestMapping(value = "/react/prac1")
	public String reactPractice1(){
		return "/WEB-INF/views/react/practice.html";
	}
}
