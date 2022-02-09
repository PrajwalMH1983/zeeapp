package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

public interface EpisodeRepository {
	public String addEpisode(Episode episode);
	public Optional<Episode> getEpisodeById(String epiId) throws IdNotFoundException , InvalidIdLengthException , InvalidNameException;
	public String updateEpisode(String epiId , Episode episode) throws IdNotFoundException;
	public String deleteEpisode(String epiId) throws IdNotFoundException;
	public Episode[] getAllEpisodes() throws InvalidIdLengthException , InvalidNameException;
	public Optional<List<Episode>> getAllEpisodeDetails() throws InvalidIdLengthException , InvalidNameException;
}
