package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository

public class SeriesRepositoryImpl implements SeriesRepository {

	DBUtils dbUtils = DBUtils.getInstance();
	
	private SeriesRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
//	private static SeriesRepository seriesRepository;
//	public static SeriesRepository getInstance() throws IOException {
//		if(seriesRepository == null)
//			seriesRepository = new SeriesRepositoryImpl();
//		return seriesRepository;
//	}
	
	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		String insertStatement = "insert into series"
				+ "(seriesId , seriesName , ageLimit , seriesGenre , seriesLength , seriesCast , releaseDate , language , noOfEpisodes , trailer)"
				+ "values(? , ? , ? , ? , ? , ? , ? , ? , ? , null)";
				
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, series.getSeriesId());
			preparedStatement.setString(2, series.getSeriesName());
			preparedStatement.setInt(3, series.getAgeLimit());
			preparedStatement.setString(4, series.getSeriesGenre());
			preparedStatement.setFloat(5, series.getSeriesLength());
			preparedStatement.setString(6, series.getSeriesCast());
			preparedStatement.setString(7, series.getSeriesReleaseDate());
			preparedStatement.setString(8, series.getSeriesLanguage());
			preparedStatement.setInt(9, series.getSeriesNoOfEpisodes());
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0) {
				connection.commit();
				return "Successful";
			} else {
				connection.rollback();
				return "Failed";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			return "Failed";
		}
		finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public String deleteSeries(String seriesId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		
		String deleteStatement = "delete from series where seriesId=?";
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, seriesId);
			
			int result = preparedStatement.executeUpdate();
			
			if(result>0) {
				connection.commit();
				return "Successful";
			}
			else {
				connection.rollback();
				return "Failed";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "Failed";
		}
		
	}

	@Override
	public String updateSeries(String seriesId, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Series> getSeriesById(String seriesId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		String selectStatement = "select * from series where seriesId=?";
		
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, seriesId);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Series series = new Series();
				series.setSeriesId(resultSet.getString("seriesId"));
				series.setSeriesName(resultSet.getString("seriesName"));
				series.setAgeLimit(resultSet.getInt("ageLimit"));
				series.setSeriesCast(resultSet.getString("seriesCast"));
				series.setSeriesGenre(resultSet.getString("seriesGenre"));
				series.setSeriesLength(resultSet.getFloat("seriesLength"));
				series.setSeriesReleaseDate(resultSet.getString("releaseDate"));
				series.setSeriesLanguage(resultSet.getString("language"));
				series.setSeriesNoOfEpisodes(resultSet.getInt("noOfEpisodes"));
				return Optional.of(series);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();

	}

	@Override
	public Series[] getAllSeries() {
		// TODO Auto-generated method stub
		Optional<List<Series>> optional = getAllSeriesDetails();
		if(optional.isEmpty())
			return null;
		else {
			List<Series> list = optional.get();
			Series[] series = new Series[list.size()];
			return list.toArray(series);
		}
	}

	@Override
	public Optional<List<Series>> getAllSeriesDetails() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		ArrayList<Series> arrayList = new ArrayList<Series>();
		
		String selectStatement = "select * from series";
		
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Series series = new Series();
				series.setSeriesId(resultSet.getString("seriesId"));
				series.setSeriesName(resultSet.getString("seriesName"));
				series.setAgeLimit(resultSet.getInt("ageLimit"));
				series.setSeriesCast(resultSet.getString("seriesCast"));
				series.setSeriesGenre(resultSet.getString("seriesGenre"));
				series.setSeriesLength(resultSet.getFloat("seriesLength"));
				series.setSeriesReleaseDate(resultSet.getString("releaseDate"));
				series.setSeriesLanguage(resultSet.getString("language"));
				series.setSeriesNoOfEpisodes(resultSet.getInt("noOfEpisodes"));
				arrayList.add(series);
			}
			return Optional.ofNullable(arrayList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}
	
//	private TreeSet<Series> set = new TreeSet<>();
//	
//	
//	private static SeriesRepository seriesRepository;
//	public static SeriesRepository getInstance() {
//		if(seriesRepository == null)
//			seriesRepository = new SeriesRepositoryImpl();
//		return seriesRepository;
//	}
//	
//	
//	public SeriesRepositoryImpl() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//	@Override
//	public String addSeries(Series series) {
//		// TODO Auto-generated method stub
//		boolean result = this.set.add(series);
//		if(result)
//			return "Added " + series + " Series";
//		return "Could'nt add " + series;
//	}
//
//	@Override
//	public String deleteSeries(String seriesId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Series> optional = this.getSeriesById(seriesId);
//		if(optional.isPresent()) {
//			boolean result = this.set.remove(optional.get());
//			if(result) {
//				return "Series with " + seriesId + " seriesId removed";
//			} else {
//				return "Could not remove the series";
//			}
//		}
//		return "Could not find that particular series";
//	}
//
//	@Override
//	public String updateSeries(String seriesId, Series series) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Series> optional = this.getSeriesById(seriesId);
//		if(optional.isPresent()) {
//			boolean result = this.set.remove(optional.get());
//			set.add(series);
//			if(result)
//				return "Updated the series with seriesId : " + seriesId;
//		}
//		return "Could not update the series";
//	}
//
//	@Override
//	public Optional<Series> getSeriesById(String seriesId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Series series1 = null;
//		for (Series series : set) {
//			if(series.getSeriesId().equals(seriesId)) {
//				series1 = series;
//				break;
//			}
//		}
//		return Optional.of(
//				Optional.ofNullable(series1)
//				.orElseThrow(()-> new IdNotFoundException("Could not find the series with series ID : " + seriesId)));
//	}
//
//	@Override
//	public List<Series> getAllSeries() {
//		// TODO Auto-generated method stub
////		Series series[] = new Series[set.size()];
////		return set.toArray(series);
//		
//		List<Series> arrList = new ArrayList<>(set);
//		Collections.sort(arrList);
//		return arrList;
//		
//	}

}
