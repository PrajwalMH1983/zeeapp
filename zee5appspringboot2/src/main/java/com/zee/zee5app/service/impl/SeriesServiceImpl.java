package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.repository.impl.SeriesRepositoryImpl;
import com.zee.zee5app.service.SeriesService;

@Service
public class SeriesServiceImpl implements SeriesService {

	private SeriesRepository seriesRepository;// = SeriesRepositoryImpl.getInstance();
	
	private SeriesServiceImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
//	private static SeriesService seriesService;
//	
//	public static SeriesService getInstance() throws IOException {
//		if(seriesService == null)
//			seriesService = new SeriesServiceImpl();
//		return seriesService;
//	}
	
	
	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		return seriesRepository.addSeries(series);
	}

	@Override
	public String deleteSeries(String seriesId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return seriesRepository.deleteSeries(seriesId);
	}

	@Override
	public String updateSeries(String seriesId, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return seriesRepository.updateSeries(seriesId, series);
	}

	@Override
	public Optional<Series> getSeriesById(String seriesId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return seriesRepository.getSeriesById(seriesId);
	}

	@Override
	public Series[] getAllSeries() {
		// TODO Auto-generated method stub
		return seriesRepository.getAllSeries();
	}

	@Override
	public Optional<List<Series>> getAllSeriesDetails() {
		// TODO Auto-generated method stub
		return seriesRepository.getAllSeriesDetails();
	}

//	private SeriesRepository repository = SeriesRepositoryImpl.getInstance();
//	private static SeriesService service;
//	
//	public static SeriesService getInstance() {
//		if(service == null)
//			service = new SeriesServiceImpl();
//		return service;
//	}
//	
//	private SeriesServiceImpl() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//	@Override
//	public String addSeries(Series series) {
//		// TODO Auto-generated method stub
//		return this.repository.addSeries(series);
//	}
//
//	@Override
//	public String deleteSeries(String seriesId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.repository.deleteSeries(seriesId);
//	}
//
//	@Override
//	public String updateSeries(String seriesId, Series series) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.repository.updateSeries(seriesId, series);
//	}
//
//	@Override
//	public Optional<Series> getSeriesById(String seriesId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.repository.getSeriesById(seriesId);
//	}
//
//	@Override
//	public List<Series> getAllSeries() {
//		// TODO Auto-generated method stub
//		return this.repository.getAllSeries();
//	}

}
