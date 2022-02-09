package com.zee.zee5app.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Episode implements Comparable<Episode> {

	private String epiId;
	private String seriesId;
	private String episodeName;
	private float episodeLength;
	private String trailer;
	
	
	public Episode(String epiId, String seriesId, String episodeName, float episodeLength, String trailer) {
		super();
		this.epiId = epiId;
		this.seriesId = seriesId;
		this.episodeName = episodeName;
		this.episodeLength = episodeLength;
		this.trailer = trailer;
	}


	@Override
	public int compareTo(Episode o) {
		// TODO Auto-generated method stub
		return o.epiId.compareTo(this.getEpiId());
	}

}
