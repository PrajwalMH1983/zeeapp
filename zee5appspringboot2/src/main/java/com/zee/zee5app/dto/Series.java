package com.zee.zee5app.dto;

import java.net.URL;

import lombok.Data;

@Data
public class Series implements Comparable<Series>{
	private String seriesName;
	private String seriesId;
	private String seriesGenre;
	private String seriesReleaseDate;
	private String seriesLanguage;
	private String seriesCast;
	private int seriesNoOfEpisodes;
	private int ageLimit;
	private float seriesLength;
	
	public Series() {
		System.out.println("Done");
	}
	
	public Series(String seriesName, String seriesId, int ageLimit ,String seriesGenre, String seriesCast ,float seriesLength ,String seriesReleaseDate,
			String seriesLanguage, int seriesNoOfEpisodes) {
		super();
		this.seriesName = seriesName;
		this.seriesId = seriesId;
		this.ageLimit = ageLimit;
		this.seriesGenre = seriesGenre;
		this.seriesCast = seriesCast;
		this.seriesLength = seriesLength;
		this.seriesReleaseDate = seriesReleaseDate;
		this.seriesLanguage = seriesLanguage;
		this.seriesNoOfEpisodes = seriesNoOfEpisodes;
		
	}

	@Override
	public int compareTo(Series o) {
		// TODO Auto-generated method stub
		return this.seriesId.compareTo(o.getSeriesId());
	}
	
	
}
