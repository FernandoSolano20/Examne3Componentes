package com.cenfotec.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cenfotec.movies.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	List<Movie> findAllByOriginaltitleContaining(String Originaltitle);
	List<Movie> findAllByBudgetBetween(int budgetStart, int budgetEnd);
	List<Movie> findAllByRuntimeBetween(int runtimeStart, int runtimeEnd);
	
	@Query("SELECT m FROM Movie m WHERE DAY(m.releasedate) = ?1 AND MONTH(m.releasedate) = ?2 AND YEAR(m.releasedate) = ?3")
	List<Movie> findByDayAndMonthAndYear(int day, int month, int year);
	
	@Query("SELECT m FROM Movie m WHERE YEAR(m.releasedate) = ?1")
	List<Movie> findByYear(int year);
	
	@Query("SELECT m FROM Movie m WHERE DAY(m.releasedate) = ?1 AND MONTH(m.releasedate) = ?2")
	List<Movie> findByDayAndMonth(int day, int month);
}
