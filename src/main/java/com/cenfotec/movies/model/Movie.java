package com.cenfotec.movies.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Data
@EqualsAndHashCode
@Entity 
@Table(name = "movie")
public class Movie implements Serializable{
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	private int id; 
	 
	private String originaltitle;   
	  
	private int budget;   
	
	private int runtime;
	
	private String originallanguage;
	
	private LocalDate releasedate;
}
