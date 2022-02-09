package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;

public interface SeriesRepository {
	public String addSeries(Series series);
	public String deleteSeries(String seriesId) throws IdNotFoundException;
	public String updateSeries(String seriesId , Series series) throws IdNotFoundException;
	public Optional<Series> getSeriesById(String seriesId) throws IdNotFoundException;
	public Series[] getAllSeries();
	public Optional<List<Series>> getAllSeriesDetails();
}
