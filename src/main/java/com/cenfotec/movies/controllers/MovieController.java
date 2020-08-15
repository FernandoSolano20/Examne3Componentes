package com.cenfotec.movies.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cenfotec.movies.model.Movie;
import com.cenfotec.movies.service.IMovieService;
import com.cenfotec.movies.service.MovieService;

@RestController 
@RequestMapping({"api/movies"})
public class MovieController {
	@Autowired
	private IMovieService service; 
	
	@GetMapping 
	public List findAll()
	{   
		return service.findAll(); 
	} 
	 
	@GetMapping(path = {"/{id}"}) 
	public ResponseEntity<Movie> findById(@PathVariable int id)
	{   
		return service.findById(id)           
				.map(record -> ResponseEntity.ok().body(record))           
				.orElse(ResponseEntity.notFound().build()); 
	} 
	 
	@PostMapping 
	public Movie create(@RequestBody Movie movie)
	{     
		return service.save(movie); 
	} 
	 
	@PutMapping(value="/{id}")   
	public ResponseEntity<Movie> update(@PathVariable("id") int id, @RequestBody Movie movie)
	{     
		return service.findById(id)         
				.map(record -> {             
					record.setOriginaltitle(movie.getOriginaltitle());             
					record.setBudget(movie.getBudget());
					record.setRuntime(movie.getRuntime());
					record.setOriginallanguage(movie.getOriginallanguage());
					record.setReleasedate(movie.getReleasedate());
					Movie updated = service.save(record);             
					return ResponseEntity.ok().body(updated);         
				}).orElse(ResponseEntity.notFound().build());   
	}
	
	@DeleteMapping(path ={"/{id}"})   
	public ResponseEntity<?> delete(@PathVariable("id") int id) 
	{     
		return service.findById(id)         
				.map(record -> {             
					service.deleteById(id);             
					return ResponseEntity.ok().build();         
					}).orElse(ResponseEntity.notFound().build());   
	}
	
	@GetMapping(value = "search")
	 public List<Movie> getMoviesFilter(@RequestParam(name = "title", required = false, defaultValue = "") String title, 
			 @RequestParam(name = "min_budget", required = false, defaultValue = "") String min_budget,
			 @RequestParam(name = "max_budget", required = false, defaultValue = "") String max_budget,
			 @RequestParam(name = "min_runtime", required = false, defaultValue = "") String min_runtime,
			 @RequestParam(name = "max_runtime", required = false, defaultValue = "") String max_runtime,
			 @RequestParam(name = "year", required = false, defaultValue = "") String year,
			 @RequestParam(name = "month", required = false, defaultValue = "") String month,
			 @RequestParam(name = "day", required = false, defaultValue = "") String day,
			 UriComponentsBuilder uriBuilder, 
			 HttpServletResponse response) 
	{
		 if(!title.isEmpty()) 
		 {
			 return service.searchByTitle(title);
		 }
		 if(!min_budget.isEmpty() && !max_budget.isEmpty())
		 {
			 return getMovieByRangeBudget(Integer.parseInt(min_budget), Integer.parseInt(max_budget));
		 }
		 
		 if(!min_runtime.isEmpty() && !max_runtime.isEmpty())
		 {
			 return getMovieByRangeRuntime(Integer.parseInt(min_runtime), Integer.parseInt(max_runtime));
		 }
		 
		 if(!year.isEmpty() && !month.isEmpty() && !day.isEmpty())
		 {
			 return getMovieByFullDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		 }
		 
		 if(!month.isEmpty() && !day.isEmpty())
		 {
			 return getMovieByDayAndMonth(Integer.parseInt(month), Integer.parseInt(day));
		 }
		 
		 if(!year.isEmpty())
		 {
			 return getMovieByYear(Integer.parseInt(year));
		 }
		 
		 return findAll();
	}
	
	public List<Movie> getMovieByRangeBudget(int min_budget, int max_budget)
	{
		return service.searchByRangeBudget(min_budget, max_budget);
	}
	
	 public List<Movie> getMovieByRangeRuntime(int min_runtime, int max_runtime) 
	{
		 return service.searchByRangeRuntime(min_runtime, max_runtime);
	}
	
	 public List<Movie> getMovieByFullDate(int year, int month, int day) 
	{
		 return service.findByDayAndMonthAndYear(day, month, year);
	}
	
	 public List<Movie> getMovieByDayAndMonth(int month, int day) 
	{
		 return service.findByDayAndMonth(day, month);
	}
	
	 public List<Movie> getMovieByYear(int year) 
	{
		 return service.findByYear(year);
	}
}
