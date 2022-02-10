package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;


public interface EpisodeService {
	public String addEpisode(Episode episode);
	public Optional<Episode> getEpisodeById(String epiId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
	public String deleteEpisode(String epiId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
	public Episode[] getAllEpisodes();
	public Optional<List<Episode>> getAllEpisodeDetails() throws InvalidIdLengthException, InvalidNameException;
}
