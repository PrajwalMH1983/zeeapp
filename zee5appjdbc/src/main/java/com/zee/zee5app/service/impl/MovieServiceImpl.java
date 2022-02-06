package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.repository.impl.MovieRepositoryImpl;
import com.zee.zee5app.service.MovieService;

public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;// = MovieRepositoryImpl.getInstance();
	
	private MovieServiceImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	//private static MovieService movieService;
	
//	public static MovieService getInstance() throws IOException {
//		if(movieService == null)
//			movieService = new MovieServiceImpl();
//		return movieService;
//	}
	
	
	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return movieRepository.addMovie(movie);
	}

	@Override
	public String deleteMovie(String movieId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return movieRepository.deleteMovie(movieId);
	}

	@Override
	public String updateMovie(String movieId, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return movieRepository.updateMovie(movieId, movie);
	}

	@Override
	public Optional<Movie> getMovieById(String movieId)
			throws IdNotFoundException, InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return movieRepository.getMovieById(movieId);
	}

	@Override
	public Movie[] getAllMovies() throws InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return  movieRepository.getAllMovies();
	}


	@Override
	public Optional<List<Movie>> getAllMovieDetails() throws InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return movieRepository.getAllMovieDetails();
	}

//	private MovieRepository repository = MovieRepositoryImpl.getInstance();
//	private static MovieService service;
//	
//	public static MovieService getInstance() throws IOException{
//		if(service == null)
//			service = new MovieServiceImpl();
//		return service;
//	}
//	
//	private MovieServiceImpl() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	@Override
//	public String addMovie(Movie movie) {
//		// TODO Auto-generated method stub
//		return this.repository.addMovie(movie);
//	}
//
//	@Override
//	public String deleteMovie(String movieId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.repository.deleteMovie(movieId);
//	}
//
//	@Override
//	public String updateMovie(String movieId, Movie movie) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.repository.updateMovie(movieId, movie);
//	}
//
//	@Override
//	public Optional<Movie> getMovieById(String movieId) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException {
//		// TODO Auto-generated method stub
//		return this.repository.getMovieById(movieId);
//	}
//
//	@Override
//	public List<Movie> getAllMovies() {
//		// TODO Auto-generated method stub
//		return this.repository.getAllMovies();
//	}

}
