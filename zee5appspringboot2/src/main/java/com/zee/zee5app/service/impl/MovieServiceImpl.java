package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;// = MovieRepositoryImpl.getInstance();
	
//	private MovieServiceImpl() throws IOException{
//		// TODO Auto-generated constructor stub
//	}
	
	//private static MovieService movieService;
	
//	public static MovieService getInstance() throws IOException {
//		if(movieService == null)
//			movieService = new MovieServiceImpl();
//		return movieService;
//	}
	
	
	@Override
	@Transactional(rollbackFor = AlreadyExistsException.class)
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		Movie movie2 = movieRepository.save(movie);
		if(movie2 != null)
			return "Successful";
		else
			return "Failed";
	}

	@Override
	public String deleteMovie(String movieId) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		Optional<Movie> optional = this.getMovieById(movieId);
		if(optional.isEmpty())
			throw new IdNotFoundException("Record not Found");
		else {
			movieRepository.deleteById(movieId);
			return "Successful";
		}
	}

	@Override
	public Optional<Movie> getMovieById(String movieId)
			throws IdNotFoundException, InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return movieRepository.findById(movieId);
	}

	@Override
	public Movie[] getAllMovies() throws InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		List<Movie> list = movieRepository.findAll();
		Movie[] movies = new Movie[list.size()];
		return list.toArray(movies);
	}


	@Override
	public Optional<List<Movie>> getAllMovieDetails() throws InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(movieRepository.findAll());
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
