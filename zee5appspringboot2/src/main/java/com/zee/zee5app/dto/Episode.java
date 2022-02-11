package com.zee.zee5app.dto;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "episodeName")}, name = "episode")
public class Episode implements Comparable<Episode> {

	@Id
	private String epiId;
	@NotBlank
	private String episodeName;
	@NotNull
	private float episodeLength;
	@NotBlank
	private String location;
	private String trailer;
	
	
	//This table should have a Foreign key seriesId
	@ManyToOne
	@JoinColumn(name = "seriesId") //To create Foreign key
	private Series series;
	
	@Override
	public int compareTo(Episode o) {
		// TODO Auto-generated method stub
		return o.epiId.compareTo(this.getEpiId());
	}

}
