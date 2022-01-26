package com.zee.zee5app.dto;

import lombok.Data;

@Data
public class Series {
	private String seriesName;
	private String seriesId;
	private String seriesGenre;
	private String seriesReleaseDate;
	private String seriesLanguage;
	private int seriesSeasons;
	private int seriesTotalEpisodes;
	private Series() {
		System.out.println("Done");
	}
	
	
}
