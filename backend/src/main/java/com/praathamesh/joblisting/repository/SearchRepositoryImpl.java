package com.praathamesh.joblisting.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.praathamesh.joblisting.model.Post;

//allows Spring to detect our custom beans automatically
@Component
public class SearchRepositoryImpl implements SearchRepository {
	
	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter converter;  //used to convert mongo object to java object
	
	@Autowired
	private Environment env;
	
	@Override
	public List<Post> findBytext(String text) {
		
		
		final List<Post> posts = new ArrayList<>();
		
		final String db_name = env.getProperty("spring.data.mongodb.database");
		MongoDatabase database = client.getDatabase(db_name);
		MongoCollection<Document> collection =  database.getCollection("jobpost");
		
		// pipeline to query(search) -> sort -> limit 5 output
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                 new Document("text",
                 new Document("query", text)
                 .append("path", Arrays.asList("techs", "desc", "profile")))),
                 new Document("$sort",
                 new Document("exp", 1L)),
                 new Document("$limit", 5L)));
		
		
		result.forEach(doc -> posts.add(converter.read(Post.class,doc)));
		return posts;
	}

}
