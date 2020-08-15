package com.cenfotec.movies.service;

import java.util.List;
import java.util.Optional;

import com.cenfotec.movies.model.Movie;


public interface IMovieService  {
	public List<Movie> findAll();
	public Optional<Movie> findById(int id);
	public Movie save(Movie movie);
	public void deleteById(int id);
	public List<Movie> searchByTitle(String query);
	public List<Movie> searchByRangeBudget(int min, int max);
	public List<Movie> searchByRangeRuntime(int min, int max);
	public List<Movie> findByDayAndMonthAndYear(int day, int month, int year);
	public List<Movie> findByYear(int year);
	public List<Movie> findByDayAndMonth(int day, int month);
}
