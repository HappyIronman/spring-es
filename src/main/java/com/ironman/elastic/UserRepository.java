package com.ironman.elastic;

import com.ironman.entity.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: ZJW
 * @Description:
 * @Date: Created in 10:14 2017/11/20 0020
 **/

public interface UserRepository extends ElasticsearchRepository<User, Long>, CustomUserRepository {
	/**
	 * 自定义查询
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@Query("{\"bool\" : {\"must\" : [ {\"term\" : {\"username\" : \"?0\"}}, {\"term\" : {\"password\" : \"?1\"}} ]}}")
	//注意：需要替换的参数？需要加双引号；需要指定参数下标，从0开始
	User findByNameAndPSW(String username, String password);

	User findTopById(Long id);

	List<User> findAllByUsernameContaining(String username);

	List<User> findByAgeBetween(int min, int max);

}
