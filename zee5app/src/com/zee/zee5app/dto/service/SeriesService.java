package com.zee.zee5app.dto.service;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.repository.SeriesRepository;

import lombok.Data;

@Data

public class SeriesService {
	private SeriesRepository repository = SeriesRepository.getInstance();

	private SeriesService() {
		
	}
	
	static SeriesService service = null;
	
	public static SeriesService getInstance() {
		if(service == null)
			service = new SeriesService();
		return service;
	}
	
	public String addSeries(Series series){
		return this.repository.addSeries(series);
	}
	
	public Series getSeriesById(String seriesId) {
		return repository.getSeriesById(seriesId);
	}
	
	public Series[] getSeries() {
		return repository.getSeries();
	}
	
	public String updateSeries(String seriesId , Series series) {
		return repository.updateSeries(seriesId, series);
	}
	
	public String deleteSeries(String seriesId) {
		return repository.deleteSeries(seriesId);
	}
}
