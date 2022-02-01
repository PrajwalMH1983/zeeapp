package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;

public interface MovieRepository {
	public String addMovie(Movie movie);
	public String deleteMovie(String movieId) throws IdNotFoundException;
	public String updateMovie(String movieId , Movie movie) throws IdNotFoundException;
	public Optional<Movie> getMovieById(String movieId) throws IdNotFoundException;
	public List<Movie> getAllMovies();
}
