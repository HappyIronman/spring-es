package com.ironman.elastic;

import com.ironman.entity.User;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @Author: ZJW
 * @Description:
 * @Date: Created in 10:17 2017/11/20 0020
 **/

public class UserRepositoryImpl implements CustomUserRepository {
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;


	@Override
	public boolean insertUser(User user){

		try {
			IndexQuery indexQuery = new IndexQueryBuilder().withObject(user).build();
			elasticsearchTemplate.index(indexQuery);
			return true;
		} catch(Exception e){
			System.out.println("insert or update user info error.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void updateUserNameById(String username, Long id) throws IOException{

		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.doc(jsonBuilder().startObject().field("username", username).endObject());

		UpdateQuery updateQuery = new UpdateQueryBuilder()
				.withClass(User.class)
				.withIndexName("user")
				.withType("id")
				.withId(id.toString())
				.withUpdateRequest(updateRequest).build();
		UpdateResponse result = elasticsearchTemplate.update(updateQuery);
		System.out.println(result);
	}
}
