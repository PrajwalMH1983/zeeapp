package com.zee.zee5app.controller;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.payload.response.MessageResponse;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.service.MovieService;

@CrossOrigin("*")
@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	MovieService movieService;
	
	@Autowired
	MovieRepository movieRepository;
	
	@PostMapping("/addMovie")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
//		String res = movieService.addMovie(movie);
		
		if(movieRepository.existsById(movie.getMovieId())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error : This MovieId Already Exists"));
		}
		
		if(movieRepository.existsByMovieName(movie.getMovieName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error : This Movie Name Already exists"));
		}
		
		return ResponseEntity.status(201).body(new MessageResponse("Movie Added Successfully"));
		
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllMovies() throws InvalidNameException, InvalidIdLengthException {
		Optional<List<Movie>> optional = movieService.getAllMovieDetails();
		
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("No movies found"));
		}
		return ResponseEntity.ok(optional.get());
	}
}
