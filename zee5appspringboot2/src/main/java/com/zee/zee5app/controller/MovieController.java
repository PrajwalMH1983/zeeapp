package com.zee.zee5app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.service.MovieService;

@RequestMapping("/movies")
public class MovieController {
	@Autowired
	MovieService movieService;
	
	@PostMapping("/addMovie")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
		String res = movieService.addMovie(movie);
		return ResponseEntity.status(201).body(res);
	}
}