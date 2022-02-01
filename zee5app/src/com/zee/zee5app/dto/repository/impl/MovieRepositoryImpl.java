package com.zee.zee5app.dto.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.repository.MovieRepository;
import com.zee.zee5app.exception.IdNotFoundException;

public class MovieRepositoryImpl implements MovieRepository {

	private Set<Movie> set = new LinkedHashSet<>();
	
	public MovieRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static MovieRepository movieRepository;
	
	public static MovieRepository getInstance() {
		if(movieRepository == null)
			movieRepository = new MovieRepositoryImpl();
		return movieRepository;
	}
	
	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		boolean result = this.set.add(movie);
		if(result) {
			return "Added Movie Sucessfully";
		}
		return "Could Not Add the Movie";
	}

	@Override
	public String deleteMovie(String movieId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Movie> optional = this.getMovieById(movieId);
		if(optional.isPresent()) {
			boolean result = set.remove(optional.get());
			if(result) {
				return "Deleted the Movie with movieId " + movieId + " Sucessfully";
			} else {
				return "Could'nt delete the movie with movieId " + movieId;
			}
		}
		return "Could'nt find the movie with movieId : " + movieId;
	}

	@Override
	public String updateMovie(String movieId, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Movie> optional = this.getMovieById(movieId);
		if(optional.isPresent()) {
			boolean result = set.remove(optional.get());
			set.add(movie);
			if(result) {
				return "Updated the movie with movieId : " + movieId;
			}
		}
		return "Could'nt update the movie with movieId : " + movieId;
	}

	@Override
	public Optional<Movie> getMovieById(String movieId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Movie movie1 = null;
		for (Movie movie : set) {
			if(movie.getMovieId().equals(movieId)) {
				movie1 = movie;
				break;
			}
		}
		return Optional.of(
				Optional.ofNullable(movie1)
				.orElseThrow(()-> new IdNotFoundException("Could not find the movie with movie ID : " + movieId)));
	}

	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		List<Movie> arrayList = new ArrayList(set);
		Collections.sort(arrayList);
		return arrayList;
	}

}
