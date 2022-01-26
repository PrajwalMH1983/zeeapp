package com.zee.zee5app.dto;

import lombok.Data;

@Data
public class Movie {
	private String movieId;
	private String movieName;
	private String movieGenre;
	private String movieReleaseDate;
	private String movieTrailer;
	private String movieLanguage;
	private int movieLength;
	
	
	public Movie() {
		
	}
	
	
	
}
