package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.service.EpisodeService;

@Service
public class EpisodeServiceImpl implements EpisodeService {

	@Autowired
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
		Episode episode2 = episodeRepository.save(episode);
		if(episode2 != null)
			return "Successful";
		else
			return "Failed";
	}

	@Override
	public Optional<Episode> getEpisodeById(String epiId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return episodeRepository.findById(epiId);
	}


	@Override
	public String deleteEpisode(String epiId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		Optional<Episode> optional = this.getEpisodeById(epiId);
		if(optional.isEmpty())
			throw new IdNotFoundException("Record not Found");
		else {
			episodeRepository.deleteById(epiId);
			return "Succesful";
		}
	}

	@Override
	public Episode[] getAllEpisodes() {
		// TODO Auto-generated method stub
		List<Episode> list = episodeRepository.findAll();
		Episode[] episodes = new Episode[list.size()];
		return list.toArray(episodes);
	}

	@Override
	public Optional<List<Episode>> getAllEpisodeDetails() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(episodeRepository.findAll());
	}

}
