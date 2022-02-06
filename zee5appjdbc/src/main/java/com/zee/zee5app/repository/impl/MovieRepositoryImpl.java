package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

	DBUtils dbUtils = DBUtils.getInstance();
	
	private MovieRepositoryImpl() throws IOException {
		// TODO Auto-generated constructor stub
	}
	
	//private static MovieRepository movieRepository;
	
//	public static MovieRepository getInstance() throws IOException {
//		if(movieRepository == null)
//			movieRepository = new MovieRepositoryImpl();
//		return movieRepository;
//	}
	

	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		
		String insertStatement = "insert into movies"
				+ "(movieId , movieName , movieGenre , movieLength , releaseDate , movieLanguage , movieCast , ageLimit)"
				+ "values(? , ? , ? , ? , ? , ? , ? , ?)";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1 , movie.getMovieId());
			preparedStatement.setString(2 , movie.getMovieName());
			preparedStatement.setString(3 , movie.getMovieGenre());
			preparedStatement.setFloat(4 , movie.getMovieLength());
			preparedStatement.setString(5 , movie.getMovieReleaseDate());
			preparedStatement.setString(6 , movie.getMovieLanguage());
			preparedStatement.setString(7 , movie.getCast().toString());
			preparedStatement.setInt(8, movie.getAgeLimit());
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0) {
				connection.commit();
				return "Successful";
			} else {
				connection.rollback();
				return "Failed";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			return "Failed";
		}
		finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public String deleteMovie(String movieId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		
		String deleteStatement = "delete from movies where movieId = ?";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, movieId);
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0) {
				connection.commit();
				return "Successful";
			} else {
				connection.rollback();
				return "Failed";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			return "Failed";
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
	}

	@Override
	public String updateMovie(String movieId, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Movie> getMovieById(String movieId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		String selectStatement = "select * from movies where movieId = ?";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, movieId);
			
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				Movie movie = new Movie();

				movie.setMovieId(resultSet.getString("movieId"));
				movie.setMovieName(resultSet.getString("movieName"));
				movie.setMovieGenre(resultSet.getString("movieGenre"));
				movie.setMovieLength(resultSet.getFloat("movieLength"));
				movie.setMovieReleaseDate(resultSet.getString("releaseDate"));
				movie.setMovieLanguage(resultSet.getString("movieLanguage"));
				movie.setCast(resultSet.getString("movieCast"));
				movie.setAgeLimit(resultSet.getInt("ageLimit"));
				
				return Optional.of(movie);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();
	}


	@Override
	public Movie[] getAllMovies() throws InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		Optional<List<Movie>> optional = getAllMovieDetails();
		if(optional.isEmpty())
			return null;
		else {
			List<Movie> list = optional.get();
			Movie[] movies = new Movie[list.size()];
			return list.toArray(movies);
		}
	}


	@Override
	public Optional<List<Movie>> getAllMovieDetails() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<Movie> arrayList = new ArrayList<Movie>();
		
		String selectStatement = "select * from movies";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			resultSet = preparedStatement.executeQuery();
			
			
			while(resultSet.next()) {
				Movie movie = new Movie();

				movie.setMovieId(resultSet.getString("movieId"));
				movie.setMovieName(resultSet.getString("movieName"));
				movie.setMovieGenre(resultSet.getString("movieGenre"));
				movie.setMovieLength(resultSet.getFloat("movieLength"));
				movie.setMovieReleaseDate(resultSet.getString("releaseDate"));
				movie.setMovieLanguage(resultSet.getString("movieLanguage"));
				movie.setCast(resultSet.getString("movieCast"));
				movie.setAgeLimit(resultSet.getInt("ageLimit"));
				
				arrayList.add(movie);
			}
			return Optional.ofNullable(arrayList);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private Set<Movie> set = new LinkedHashSet<>();
//	
//	private static MovieRepository movieRepository;
//	
//	public static MovieRepository getInstance() {
//		if(movieRepository == null)
//			movieRepository = new MovieRepositoryImpl();
//		return movieRepository;
//	}
//	
//	@Override
//	public String addMovie(Movie movie) {
//		// TODO Auto-generated method stub
//		boolean result = this.set.add(movie);
//		if(result) {
//			return "Added Movie Sucessfully";
//		}
//		return "Could Not Add the Movie";
//	}
//
//	@Override
//	public String deleteMovie(String movieId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Movie> optional = this.getMovieById(movieId);
//		if(optional.isPresent()) {
//			boolean result = set.remove(optional.get());
//			if(result) {
//				return "Deleted the Movie with movieId " + movieId + " Sucessfully";
//			} else {
//				return "Could'nt delete the movie with movieId " + movieId;
//			}
//		}
//		return "Could'nt find the movie with movieId : " + movieId;
//	}
//
//	@Override
//	public String updateMovie(String movieId, Movie movie) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Movie> optional = this.getMovieById(movieId);
//		if(optional.isPresent()) {
//			boolean result = set.remove(optional.get());
//			set.add(movie);
//			if(result) {
//				return "Updated the movie with movieId : " + movieId;
//			}
//		}
//		return "Could'nt update the movie with movieId : " + movieId;
//	}
//
//	@Override
//	public Optional<Movie> getMovieById(String movieId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Movie movie1 = null;
//		for (Movie movie : set) {
//			if(movie.getMovieId().equals(movieId)) {
//				movie1 = movie;
//				break;
//			}
//		}
//		return Optional.of(
//				Optional.ofNullable(movie1)
//				.orElseThrow(()-> new IdNotFoundException("Could not find the movie with movie ID : " + movieId)));
//	}
//
//	@Override
//	public List<Movie> getAllMovies() {
//		// TODO Auto-generated method stub
//		List<Movie> arrayList = new ArrayList(set);
//		Collections.sort(arrayList);
//		return arrayList;
//	}

}
