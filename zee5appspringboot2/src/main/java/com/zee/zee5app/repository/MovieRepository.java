package com.zee.zee5app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
	//find : retrieve details based on movieName and and movieLanguage
	//Boolean : gives exists or not
	
	//Boolean existsByMovieNameAndMovieLanguage(String movieName , String movieLanguage);
	Optional<Movie> findByMovieName(String movieName);
	Optional<Movie> findByMovieNameAndMovieLanguage(String movieName , String movieLangauge);
	Optional<Movie> findByMovieNameAndMovieReleaseDate(String movieName , String movieReleaseDate);
	Optional<Movie> findByCast(String cast);
}
