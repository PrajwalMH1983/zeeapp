package com.zee.zee5app.dto.service.impl;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.repository.MovieRepository;
import com.zee.zee5app.dto.repository.impl.MovieRepositoryImpl;
import com.zee.zee5app.dto.service.MovieService;
import com.zee.zee5app.exception.IdNotFoundException;

public class MovieServiceImpl implements MovieService {

	private MovieRepository repository = MovieRepositoryImpl.getInstance();
	private static MovieService service;
	
	public static MovieService getInstance() {
		if(service == null)
			service = new MovieServiceImpl();
		return service;
	}
	
	private MovieServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return this.repository.addMovie(movie);
	}

	@Override
	public String deleteMovie(String movieId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.deleteMovie(movieId);
	}

	@Override
	public String updateMovie(String movieId, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.updateMovie(movieId, movie);
	}

	@Override
	public Optional<Movie> getMovieById(String movieId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.getMovieById(movieId);
	}

	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return this.repository.getAllMovies();
	}

}
