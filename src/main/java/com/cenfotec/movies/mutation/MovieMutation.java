package com.cenfotec.movies.mutation;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cenfotec.movies.model.Movie;
import com.cenfotec.movies.service.MovieService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component 
public class MovieMutation implements GraphQLMutationResolver  
{
	@Autowired 
	private MovieService service;
	
	public Movie createMovie(String originaltitle, int budget, int runtime, String originallanguage, String releasedate) {
		Movie movie = new Movie();
		movie.setOriginaltitle(originaltitle);
		movie.setBudget(budget);
		movie.setRuntime(runtime);
		movie.setOriginallanguage(originallanguage);
		movie.setReleasedate(LocalDate.parse(releasedate));
		return service.save(movie);
	}
	
	public Movie updateMovie(int id, String originaltitle, int budget, int runtime, String originallanguage, String releasedate) {
		Optional<Movie> movieOptional = service.findById(id);
		if(movieOptional.isPresent()) 
		{
			Movie movie = movieOptional.get();
			movie.setOriginaltitle(originaltitle);
			movie.setBudget(budget);
			movie.setRuntime(runtime);
			movie.setOriginallanguage(originallanguage);
			movie.setReleasedate(LocalDate.parse(releasedate));
			return service.save(movie);
		}
		return null;
	}
	
	public boolean deleteMovie(int id) {
		service.deleteById(id);
		return true;
	}
}
