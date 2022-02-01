package com.zee.zee5app.dto.repository;

import com.zee.zee5app.dto.Series;

public class SeriesRepository2 {
	private Series[] series = new Series[10];
	private static int count = -1;
	private SeriesRepository2() {
		
	}
	
	private static SeriesRepository2 seriesRepository;
	public static SeriesRepository2 getInstance() {
		if(seriesRepository == null) {
			seriesRepository = new SeriesRepository2();
		}
		return seriesRepository;
	}
	
	public String addSeries(Series series1) {
		if(count == series.length - 1) {
			Series temp[] = new Series[series.length * 2];
			System.arraycopy(series, 0, temp, 0 , series.length);
			series = temp;
			series[++count] = series1;
			
			return "Array is Full";
		}
		series[++count] = series1;
		return "Success";
	}
	
	public Series[] getSeries() {
		return series;
	}
	
	public Series getSeriesById(String seriesId) {
		for (Series series2 : series) {
			if(series2 != null && series2.getSeriesId().equals(seriesId))
				return series2;
		}
		return null;
	}
	
	public String updateSeries(String seriesId , Series series1) {
		int count1 = 0;
		for (Series series2 : series) {
			if(series2 != null && series2.getSeriesId().equals(seriesId)) {
				series[count1] = series1;
				return "Done";
			}
			++count1;
		}
		return "Not Done";
	}
	
	public String deleteSeries(String seriesId) {
		int count1 = 0;
		for (Series series2 : series) {
			if(series2 != null && series2.getSeriesId().equals(seriesId)) {
				System.arraycopy(series, count1 + 1, series, count1, series.length - count1 - 1);
				series[series.length - 1] = null;
				return "Done";
			}
			++count1;
		}
		return "Not Done";
	}
}
