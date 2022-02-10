package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface MovieService {
	public String addMovie(Movie movie);
	public String deleteMovie(String movieId) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException;
	public Optional<Movie> getMovieById(String movieId) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException;
	public Movie[] getAllMovies() throws InvalidNameException, InvalidIdLengthException;
	public Optional<List<Movie>> getAllMovieDetails() throws InvalidNameException, InvalidIdLengthException;
	
}
