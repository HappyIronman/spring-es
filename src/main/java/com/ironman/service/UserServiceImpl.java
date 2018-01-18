package com.ironman.service;

import com.ironman.elastic.UserRepository;
import com.ironman.entity.User;
import com.ironman.serviceInter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @Author: ZJW
 * @Description:
 * @Date: Created in 9:54 2017/11/20 0020
 **/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	public User getUserById(Long id){

		User user = userRepository.findTopById(id);
		if(user != null){
			System.out.println("a user got");
		}
		return user;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password){
		User user = userRepository.findByNameAndPSW(username, password);
		System.out.println(user);
		return user;
	}

	@Override
	public void insertUser(User user){
		user.setId(System.currentTimeMillis());
		userRepository.insertUser(user);
	}

	@Override
	public List<User> getAllUser(){
		List<User> userList = new ArrayList<User>();
		Iterable<User> users = userRepository.findAll();
		Iterator it = users.iterator();
		while (it.hasNext()) {
			User user = (User) it.next();
			System.out.println(user);
			userList.add(user);
		}
		return userList;
	}

	@Override
	public void UpdateUserName(String username, Long id) throws IOException{
		userRepository.updateUserNameById(username, id);
	}

	@Override
	public void deleteUserById(Long id){
		userRepository.delete(id);
	}
}