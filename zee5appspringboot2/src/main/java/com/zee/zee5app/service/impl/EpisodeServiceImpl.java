package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.repository.impl.EpisodeRepositoryImpl;
import com.zee.zee5app.service.EpisodeService;

@Service
public class EpisodeServiceImpl implements EpisodeService {

	private EpisodeRepository episodeRepository;// = EpisodeRepositoryImpl.getInstance();
	
	private EpisodeServiceImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	//private static EpisodeService episodeService;
	
//	public static EpisodeService getInstance() throws IOException {
//		if(episodeService == null)
//			episodeService = new EpisodeServiceImpl();
//		return episodeService;
//	}
	
	@Override
	public String addEpisode(Episode episode) {
		// TODO Auto-generated method stub
		return episodeRepository.addEpisode(episode);
	}

	@Override
	public Optional<Episode> getEpisodeById(String epiId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return episodeRepository.getEpisodeById(epiId);
	}

	@Override
	public String updateEpisode(String epiId, Episode episode) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return episodeRepository.updateEpisode(epiId, episode);
	}

	@Override
	public String deleteEpisode(String epiId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return episodeRepository.deleteEpisode(epiId);
	}

	@Override
	public Episode[] getAllEpisodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Episode>> getAllEpisodeDetails() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return episodeRepository.getAllEpisodeDetails();
	}

}
