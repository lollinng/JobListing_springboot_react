package com.praathamesh.joblisting.controller;

import com.praathamesh.joblisting.model.Post;
import com.praathamesh.joblisting.repository.PostRepository;
import com.praathamesh.joblisting.repository.SearchRepository;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Controller to handle requests related to job posts.
 * This class defines endpoints for accessing job post data and redirecting to the API documentation.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000") // for cors
public class PostController {
	
	// The @Autowired annotation ensures that the repo field in PostController is properly 
	// injected with the instance of PostRepository.
    @Autowired
    PostRepository repo;
    
    @Autowired
    SearchRepository srepo;
    
    /**
     * Redirects the root URL ("/") to the Swagger UI page for API documentation.
     * This method is ignored by Swagger documentation generation.
     *
     * @param response the HttpServletResponse object to manage the redirection.
     * @throws IOException if an input or output exception occurs during redirection.
     */
    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    /**
     * Endpoint to retrieve all job posts.
     * This method handles GET requests to the "/posts" URL and returns a list of all job posts.
     *
     * @return a list of Post objects representing all job posts.
     */
    @GetMapping("/allPosts")
    @CrossOrigin
    public List<Post> getAllPosts() {
        return repo.findAll();
    }

    
 
    // post mapping
    @PostMapping("/post")
    @CrossOrigin
    // accepts request body of type Post
    public Post addPost(@RequestBody Post post) {
    	return repo.save(post);
    	
    }
    
 // post mapping
    @GetMapping("/posts/{text}") 
    @CrossOrigin
    // accepts request body of type Post
    public List<Post> search(@PathVariable String text) {
    	return srepo.findBytext(text);
    }
    
    
    
}