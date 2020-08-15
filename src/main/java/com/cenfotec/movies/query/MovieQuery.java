package com.cenfotec.movies.query;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cenfotec.movies.model.Movie;
import com.cenfotec.movies.service.MovieService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class MovieQuery  implements GraphQLQueryResolver  {
	@Autowired
	private MovieService service;
	
	public List<Movie> getMovies() 
	{
		return this.service.findAll();
	}
	
	public Optional<Movie> getMovie(int id) {
		return this.service.findById(id);
	}
	
	public List<Movie> findByTitle(String title) {
		return this.service.searchByTitle(title);
	}
	
	public List<Movie> findByYear(int year) {
		return this.service.findByYear(year);
	}
}
