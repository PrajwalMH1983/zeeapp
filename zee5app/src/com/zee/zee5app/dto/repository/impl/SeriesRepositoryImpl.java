package com.zee.zee5app.dto.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.repository.SeriesRepository;
import com.zee.zee5app.exception.IdNotFoundException;

public class SeriesRepositoryImpl implements SeriesRepository {
	
	private TreeSet<Series> set = new TreeSet<>();
	
	
	private static SeriesRepository seriesRepository;
	public static SeriesRepository getInstance() {
		if(seriesRepository == null)
			seriesRepository = new SeriesRepositoryImpl();
		return seriesRepository;
	}
	
	
	public SeriesRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		boolean result = this.set.add(series);
		if(result)
			return "Added " + series + " Series";
		return "Could'nt add " + series;
	}

	@Override
	public String deleteSeries(String seriesId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Series> optional = this.getSeriesById(seriesId);
		if(optional.isPresent()) {
			boolean result = this.set.remove(optional.get());
			if(result) {
				return "Series with " + seriesId + " seriesId removed";
			} else {
				return "Could not remove the series";
			}
		}
		return "Could not find that particular series";
	}

	@Override
	public String updateSeries(String seriesId, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Series> optional = this.getSeriesById(seriesId);
		if(optional.isPresent()) {
			boolean result = this.set.remove(optional.get());
			set.add(series);
			if(result)
				return "Updated the series with seriesId : " + seriesId;
		}
		return "Could not update the series";
	}

	@Override
	public Optional<Series> getSeriesById(String seriesId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Series series1 = null;
		for (Series series : set) {
			if(series.getSeriesId().equals(seriesId)) {
				series1 = series;
				break;
			}
		}
		return Optional.of(
				Optional.ofNullable(series1)
				.orElseThrow(()-> new IdNotFoundException("Could not find the series with series ID : " + seriesId)));
	}

	@Override
	public List<Series> getAllSeries() {
		// TODO Auto-generated method stub
//		Series series[] = new Series[set.size()];
//		return set.toArray(series);
		
		List<Series> arrList = new ArrayList<>(set);
		Collections.sort(arrList);
		return arrList;
		
	}

}
