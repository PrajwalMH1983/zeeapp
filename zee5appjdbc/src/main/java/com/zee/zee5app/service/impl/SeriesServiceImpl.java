package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.repository.impl.SeriesRepositoryImpl;
import com.zee.zee5app.service.SeriesService;

public class SeriesServiceImpl implements SeriesService {

	private SeriesRepository repository = SeriesRepositoryImpl.getInstance();
	private static SeriesService service;
	
	public static SeriesService getInstance() {
		if(service == null)
			service = new SeriesServiceImpl();
		return service;
	}
	
	private SeriesServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		return this.repository.addSeries(series);
	}

	@Override
	public String deleteSeries(String seriesId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.deleteSeries(seriesId);
	}

	@Override
	public String updateSeries(String seriesId, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.updateSeries(seriesId, series);
	}

	@Override
	public Optional<Series> getSeriesById(String seriesId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.getSeriesById(seriesId);
	}

	@Override
	public List<Series> getAllSeries() {
		// TODO Auto-generated method stub
		return this.repository.getAllSeries();
	}

}
