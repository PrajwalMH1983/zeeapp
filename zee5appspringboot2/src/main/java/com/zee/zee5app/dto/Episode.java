package com.zee.zee5app.dto;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
@Table(name = "episode")
public class Episode implements Comparable<Episode> {

	@Id
	private String epiId;
	@NotBlank
	private String seriesId;
	@NotBlank
	private String episodeName;
	@NotNull
	private float episodeLength;
	@NotBlank
	private String location;
	private String trailer;
	
	@Override
	public int compareTo(Episode o) {
		// TODO Auto-generated method stub
		return o.epiId.compareTo(this.getEpiId());
	}

}
