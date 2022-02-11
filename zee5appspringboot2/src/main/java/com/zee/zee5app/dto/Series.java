package com.zee.zee5app.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@EqualsAndHashCode
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "series", uniqueConstraints = {@UniqueConstraint(columnNames = "seriesName")})
public class Series implements Comparable<Series>{
	
	@Id
	private String seriesId;
	@NotBlank
	private String seriesName;
	@Max(value = 70)
	private int ageLimit;
	private String trailer;
	@NotBlank
	private String seriesCast;
	@NotBlank
	private String seriesGenre;
	@NotNull
	private String seriesReleaseDate;
	@NotBlank
	private String seriesLanguage;
	@Min(value = 1)
	private int seriesNoOfEpisodes;
	
	
	
	@OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
	private List<Episode> episodes = new ArrayList<>();

	@Override
	public int compareTo(Series o) {
		// TODO Auto-generated method stub
		return this.seriesId.compareTo(o.getSeriesId());
	}



	
	
}
