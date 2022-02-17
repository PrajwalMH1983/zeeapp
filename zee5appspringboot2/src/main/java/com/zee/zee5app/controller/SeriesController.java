package com.zee.zee5app.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.payload.response.MessageResponse;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.service.SeriesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/series")
public class SeriesController {
	@Autowired
	SeriesRepository seriesRepository;
	
	@Autowired
	SeriesService seriesService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addSeries")
	public ResponseEntity<?> addSeries(@Valid @RequestBody Series series) {
		if(seriesRepository.existsById(series.getSeriesId())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Series Id already exists"));
		}
		
		if(seriesRepository.existsBySeriesName(series.getSeriesName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Series name already exists"));
		}
		
		seriesRepository.save(series);
		return ResponseEntity.status(201).body(new MessageResponse("Series Inserted succesfully"));
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
	public ResponseEntity<?> getAllSeries() {
		List<Series> series = seriesRepository.findAll();
		if(series.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.body(new MessageResponse("No Series Found"));
		}
		return ResponseEntity.ok(series);
	}
}
