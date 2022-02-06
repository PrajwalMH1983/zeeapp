package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository

public class EpisodeRepositoryImpl implements EpisodeRepository {

	
	private DBUtils dbUtils = DBUtils.getInstance();
	
	private EpisodeRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
//	private static EpisodeRepository episodeRepository;
//	
//	//Singleton implementation
//	public static EpisodeRepository getInstance() throws IOException {
//		if(episodeRepository == null)
//			episodeRepository = new EpisodeRepositoryImpl();
//		return episodeRepository;
//	}
	
	@Override
	public String addEpisode(Episode episode) {
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		
		String insertStatement = "insert into episodes"
				+ "(epiId , seriesId , episodeName , episodeLength)"
				+ "values(? , ? , ? , ?)";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, episode.getEpiId());
			preparedStatement.setString(2, episode.getSeriesId());
			preparedStatement.setString(3, episode.getEpisodeName());
			preparedStatement.setFloat(4, episode.getEpisodeLength());
			
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
	public Optional<Episode> getEpisodeById(String epiId) throws IdNotFoundException , InvalidIdLengthException , InvalidNameException{
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		String selectStatement = "select * from episodes where epiId = ?";
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, epiId);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Episode episode = new Episode();
				episode.setEpiId(resultSet.getString("epiId"));
				episode.setSeriesId(resultSet.getString("seriesId"));
				episode.setEpisodeName(resultSet.getString("episodeName"));
				episode.setEpisodeLength(resultSet.getFloat("episodeLength"));
				
				return Optional.of(episode);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public String updateEpisode(String epiId, Episode episode) throws IdNotFoundException{
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		
		String updateStatement = "update episodes"
				+ "set epiId = ? , seriesId = ? , episodeName = ? , episodeLength = ?"
				+ "where (epiId = ?)";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, episode.getEpiId());
			preparedStatement.setString(2, episode.getSeriesId());
			preparedStatement.setString(3, episode.getEpisodeName());
			preparedStatement.setFloat(4, episode.getEpisodeLength());
			preparedStatement.setString(5, epiId);
			
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				connection.commit();
				return "Success";
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
	public String deleteEpisode(String epiId) throws IdNotFoundException{
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		
		String deleteStatement = "delete from episodes where epiId = ?";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			
			preparedStatement.setString(1, epiId);
			
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
	public Episode[] getAllEpisodes() throws InvalidIdLengthException , InvalidNameException{
		// TODO Auto-generated method stub
		Optional<List<Episode>> optional = getAllEpisodeDetails();
		if(optional.isEmpty())
			return null;
		else {
			List<Episode> list = optional.get();
			Episode[] episodes = new Episode[list.size()];
			return list.toArray(episodes);
		}
	}

	@Override
	public Optional<List<Episode>> getAllEpisodeDetails() throws InvalidIdLengthException , InvalidNameException {
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		ArrayList<Episode> arrayList = new ArrayList<>();
		
		String selectStatement = "select * from episodes";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Episode episode = new Episode();
				episode.setEpiId(resultSet.getString("epiId"));
				episode.setSeriesId(resultSet.getString("seriesId"));
				episode.setEpisodeName(resultSet.getString("episodeName"));
				episode.setEpisodeLength(resultSet.getFloat("episodeLength"));
				
				arrayList.add(episode);
			}
			return Optional.ofNullable(arrayList);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

}
