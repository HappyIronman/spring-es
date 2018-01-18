package com.ironman.elastic;

import com.ironman.entity.User;

import java.io.IOException;

/**
 * @Author: ZJW
 * @Description:
 * @Date: Created in 10:18 2017/11/20 0020
 **/
public interface CustomUserRepository {
	boolean insertUser(User user);


	/**
	 * 自定义update
	 * @param username
	 * @param id
	 */
	void updateUserNameById(String username,Long id) throws IOException;
}
