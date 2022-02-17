package com.zee.zee5app.controller;

import java.util.List;
import java.util.Optional;

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

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.payload.request.EpisodeRequest;
import com.zee.zee5app.payload.response.MessageResponse;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.repository.SeriesRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/episode")
public class EpisodeController {
	
	@Autowired
	private EpisodeRepository episodeRepository;
	
	@Autowired
	private SeriesRepository seriesRepository;
	
	@PostMapping("/addEpisode")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addEpisode(@Valid @RequestBody EpisodeRequest episodeRequest) {
		if (episodeRepository.existsById(episodeRequest.getId())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Episode Id already exists!"));
		}
		if (seriesRepository.existsById(episodeRequest.getSeriesId()) == false) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Series Id doesnot exist!"));
		}
		Optional<Series> optional = seriesRepository.findById(episodeRequest.getSeriesId());
		Episode episode = new Episode(episodeRequest.getId(),
				episodeRequest.getName(),
				episodeRequest.getLength(),
				episodeRequest.getLocation(),
				episodeRequest.getTrailer(),
				optional.get());
		episodeRepository.save(episode);
		return ResponseEntity
				.status(201)
				.body(new MessageResponse("Episode added successfully"));
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllEpisodes() {
		List<Episode> episodes = episodeRepository.findAll();
		if (episodes.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.body(new MessageResponse("No record found"));
		}
		return ResponseEntity.ok(episodes);
	}
}
