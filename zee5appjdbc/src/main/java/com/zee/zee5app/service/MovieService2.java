package com.zee.zee5app.service;

import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.repository.MovieRepository2;

import lombok.Data;

@Data

public class MovieService2 {
	private MovieRepository2 repository = MovieRepository2.getInstance();

	private MovieService2() {
		
	}
	
	static MovieService2 service = null;
	
	public static MovieService2 getInstance() {
		if(service == null)
			service = new MovieService2();
		return service;
	}
	
	public String addMovie(Movie movie) {
		return this.repository.addMovie(movie);
	}
	
	public Movie getMovieById(String movieId) {
		return repository.getMovieById(movieId);
	}
	
	public Movie[] getMovies() {
		return repository.getMovies();
	}
	
	public String updateMovie(String movieId , Movie movie) {
		return repository.updateMovie(movieId, movie);
	}
	
	public String deleteMovie(String movieId) {
		return repository.deleteMovie(movieId);
	}
}
