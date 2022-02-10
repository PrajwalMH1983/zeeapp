package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
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
@Table(name = "movie", uniqueConstraints = {@UniqueConstraint(columnNames = "movieName")})
public class Movie implements Comparable<Movie>{
	
	@Id
	private String movieId;
	@NotBlank
	private String movieName;
	@NotBlank
	private String movieGenre;
	@NotBlank
	private String movieReleaseDate;
	@NotBlank
	private String movieTrailer;
	@NotBlank
	private String movieLanguage;
	@NotNull
	private float movieLength;
	@NotBlank
	private String cast;
	@Max(value = 70)
	private int ageLimit;
	

	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		//For Ascending order
		//return this.movieId.compareTo(o.getMovieId());
		
		//For Descending order
		return o.movieId.compareTo(this.getMovieId());
	}
	
}
