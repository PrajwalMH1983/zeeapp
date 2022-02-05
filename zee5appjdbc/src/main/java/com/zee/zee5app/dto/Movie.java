package com.zee.zee5app.dto;

import java.net.URL;
import java.sql.ResultSet;

import javax.naming.InvalidNameException;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Movie implements Comparable<Movie>{
	
	@Setter(value = AccessLevel.NONE)
	private String movieId;
	@Setter(value = AccessLevel.NONE)
	private String movieName;
	private String movieGenre;
	private String movieReleaseDate;
	private URL movieTrailer;
	private String movieLanguage;
	private float movieLength;
	private String cast;
	private int ageLimit;
	

	public Movie(String movieId, String movieName, String movieGenre, String movieReleaseDate, URL movieTrailer,
			String movieLanguage, float movieLength, String cast , int ageLimit) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieReleaseDate = movieReleaseDate;
		this.movieTrailer = movieTrailer;
		this.movieLanguage = movieLanguage;
		this.movieLength = movieLength;
		this.ageLimit = ageLimit;
		this.cast = cast;
	}
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	public void setMovieId(String movieId) throws InvalidIdLengthException {
		if(movieId.length() < 6) {
			//Raise InvalidId Exception
			throw new InvalidIdLengthException("Movie Id length is less than or equal to 6");
		}
		this.movieId = movieId;
	}
	
	public void setMovieName(String movieName) throws InvalidNameException {
		if(movieName == null || movieName == "" || movieName.length() < 2) {
			throw new InvalidNameException("Movie Name is not valid");
		}
		this.movieName = movieName;
	}
	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		//For Ascending order
		//return this.movieId.compareTo(o.getMovieId());
		
		//For Descending order
		return o.movieId.compareTo(this.getMovieId());
	}
	
}
