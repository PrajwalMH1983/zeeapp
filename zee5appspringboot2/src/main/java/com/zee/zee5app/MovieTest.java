package com.zee.zee5app;

import javax.naming.InvalidNameException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.MovieService;

@SpringBootApplication
public class MovieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(MovieTest.class, args);
		
		//MovieRepository movieRepository = applicationContext.getBean(MovieRepository.class);
		
		MovieService movieService = applicationContext.getBean(MovieService.class);
		System.out.println("ADD MOVIE");
		for (int i = 1; i <= 5; i++) {
			//Movie movie = new Movie("mov000"+i, "MovieName "+i, "Genre"+i, "2022-04-"+i ,"Link"+i, "Language"+i , 120 + i, "Cast " + i , 3 + i);
			//System.out.println(movieService.addMovie(movie) + " " + i);
		}
		System.out.println();
		
		System.out.println("GET MOVIE BY ID :");
		try {
			System.out.println(movieService.getMovieById("mov0004").get());
		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println();
		
		System.out.println("GET ALL MOVIE DETAILS IN LIST");
		try {
			movieService.getAllMovieDetails().get().forEach(e->System.out.println(e));
		} catch (InvalidNameException | InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
		System.out.println("DELETE MOVIE BY ID : ");
		try {
			System.out.println(movieService.deleteMovie("mov0003"));
		} catch (IdNotFoundException | InvalidNameException | InvalidIdLengthException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println();
		
		System.out.println("GET ALL MOVIE DETAILS IN AN ARRAY : ");
		try {
			for (Movie movie : movieService.getAllMovies()) {
				System.out.println(movie);
			}
		} catch (InvalidNameException | InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
		//System.out.println(movieRepository.existsByMovieNameAndMovieLanguage("MovieName 1", "Language1"));
		
		applicationContext.close();
	}

}
