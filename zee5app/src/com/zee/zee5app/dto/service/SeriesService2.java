package com.zee.zee5app.dto.service;

import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.repository.SeriesRepository;
import com.zee.zee5app.dto.repository.SeriesRepository2;
import com.zee.zee5app.dto.repository.impl.SeriesRepositoryImpl;

import lombok.Data;

@Data

public class SeriesService2 {
	private SeriesRepository2 repository = SeriesRepository2.getInstance();

	private SeriesService2() {
		
	}
	
	static SeriesService2 service = null;
	
	public static SeriesService2 getInstance() {
		if(service == null)
			service = new SeriesService2();
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
