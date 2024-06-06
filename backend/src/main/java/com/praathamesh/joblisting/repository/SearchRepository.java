package com.praathamesh.joblisting.repository;

import com.praathamesh.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {
	List<Post> findBytext(String text);
}
