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
	private int seriesSeasons;
	private int seriesTotalEpisodes;
	
	public Series() {
		System.out.println("Done");
	}
	
	public Series(String seriesName, String seriesId, String seriesGenre, String seriesReleaseDate,
			String seriesLanguage, int seriesSeasons, int seriesTotalEpisodes) {
		super();
		this.seriesName = seriesName;
		this.seriesId = seriesId;
		this.seriesGenre = seriesGenre;
		this.seriesReleaseDate = seriesReleaseDate;
		this.seriesLanguage = seriesLanguage;
		this.seriesSeasons = seriesSeasons;
		this.seriesTotalEpisodes = seriesTotalEpisodes;
	}

	@Override
	public int compareTo(Series o) {
		// TODO Auto-generated method stub
		return this.seriesId.compareTo(o.getSeriesId());
	}
	
	
}
