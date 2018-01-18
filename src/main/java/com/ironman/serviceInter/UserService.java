package com.ironman.serviceInter;

import com.ironman.entity.User;

import java.io.IOException;
import java.util.List;

/**
 * @Author: ZJW
 * @Description:
 * @Date: Created in 9:52 2017/11/20 0020
 **/
public interface UserService {
	User getUserById(Long id);

	User getUserByUsernameAndPassword(String username, String password);

	void insertUser(User user);

	List<User> getAllUser();

	void UpdateUserName(String username, Long id) throws IOException;

	void deleteUserById(Long id);
}
