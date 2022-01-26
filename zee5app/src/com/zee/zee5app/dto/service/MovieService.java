package com.zee.zee5app.dto.service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.repository.MovieRepository;

import lombok.Data;

@Data

public class MovieService {
	private MovieRepository repository = MovieRepository.getInstance();

	private MovieService() {
		
	}
	
	static MovieService service = null;
	
	public static MovieService getInstance() {
		if(service == null)
			service = new MovieService();
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
