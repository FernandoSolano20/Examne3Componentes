package com.cenfotec.movies.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cenfotec.movies.model.Movie;
import com.cenfotec.movies.repository.MovieRepository;

@Service
public class MovieService implements IMovieService {
	@Autowired
    private MovieRepository repository;

	@Override
    public List<Movie> findAll() 
	{
    	return repository.findAll();
    }
	
	@Override
    public Optional<Movie> findById(int id) 
	{
    	return repository.findById(id);
    }
	
	@Override
    public Movie save(Movie movie) 
	{
    	return repository.save(movie);
    }
	
	@Override
    public void deleteById(int id) 
	{
    	repository.deleteById(id);
    }
	
    @Override
    public List<Movie> searchByTitle(String query) 
    {
    	return repository.findAllByOriginaltitleContaining(query);
    }
    
    @Override
    public List<Movie> searchByRangeBudget(int min, int max)
    {
    	return repository.findAllByBudgetBetween(min, max);
    }
    
    @Override
    public List<Movie> searchByRangeRuntime(int min, int max)
    {
    	return repository.findAllByRuntimeBetween(min, max);
    }
    
    @Override
    public List<Movie> findByDayAndMonthAndYear(int day, int month, int year)
    {
    	return repository.findByDayAndMonthAndYear(day, month, year);
    }
    
    @Override
    public List<Movie> findByYear(int year)
    {
    	return repository.findByYear(year);
    }
    
    @Override
    public List<Movie> findByDayAndMonth(int day, int month)
    {
    	return repository.findByDayAndMonth(day, month);
    }
}
