package com.praathamesh.joblisting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.praathamesh.joblisting.model.Post;

// interface extending the mongoDB repo , which will take care of crud operations 
// and type of data which we want to fetch which is POST , primarykey
public interface PostRepository extends MongoRepository<Post,String>{
	
}
