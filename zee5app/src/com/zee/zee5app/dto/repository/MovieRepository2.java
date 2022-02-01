package com.zee.zee5app.dto.repository;

import java.util.Optional;

import com.zee.zee5app.dto.Movie;

import lombok.Data;

@Data

public class MovieRepository2 {
	private Movie[] movies = new Movie[10];
	private static int count = -1;
	private MovieRepository2() {
		
	}
	
	private static MovieRepository2 movieRepository;
	
	public static MovieRepository2 getInstance() {
		if(movieRepository == null)
			movieRepository = new MovieRepository2();
		return movieRepository;
	}
	
	public String addMovie(Movie movie) {
		if(count == movies.length - 1) {
			Movie temp[] = new Movie[movies.length * 2];
			
			System.arraycopy(movies, 0 , temp, 0 , movies.length);
			movies = temp;
			
			movies[++count] = movie;
			
			return "Array is Full";
		}
		movies[++count]	= movie;
		
		return "Added Movie Sucessfully";
	}
	
	public Movie[] getMovies() {
		return movies;
	}
	
	public Movie getMovieById(String movieId) {
		for (Movie movie : movies) {
			if(movie != null && movie.getMovieId().equals(movieId) ) {
				return movie;
			}	
		}
		return null;
	}
	public String updateMovie(String movieId , Movie movie1) {
		int count1 = 0;
		for (Movie movie : movies) {
			if(movie != null && movie.getMovieId().equals(movieId))
			{
				movies[count1] = movie1;
				return "Done";
			}
		}
		return "Not Done";
	}
	
	public String deleteMovie(String movieId) {
		int count1 = 0;
		for (Movie movie : movies) {
			if(movie != null && movie.getMovieId().equals(movieId)) {
				System.arraycopy(movies, count1 + 1, movies, count1, movies.length - count1 - 1);
				movies[movies.length - 1] = null;
				return "Deleted The Movie";
			}
			++count1;
		}
		return "Could Not Delete The Movie";
	}
}
