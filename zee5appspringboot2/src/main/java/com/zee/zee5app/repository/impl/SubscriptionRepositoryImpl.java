package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SubscriptionRepository;


@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

	//private DBUtils dbUtils = DBUtils.getInstance();
	
	@Autowired
	DataSource dataSource;
	
	public SubscriptionRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	//private static SubscriptionRepository subscriptionRepository;
	
//	public static SubscriptionRepository getInstance() throws IOException {
//		if(subscriptionRepository == null)
//			subscriptionRepository = new SubscriptionRepositoryImpl();
//		return subscriptionRepository;
//	}
	
	@Override
	public String addSubscription(Subscription subscription) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		String insertStatement = "insert into subscription"
				+ " (subId, dateOfPayment, expiryDate, amount, paymentMode, status, subType, autoRenewal, regId)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, subscription.getSubId());
			preparedStatement.setString(2, subscription.getDateOfPayment());
			preparedStatement.setString(3, subscription.getSubExpiry());
			preparedStatement.setFloat(4, subscription.getSubAmount());
			preparedStatement.setString(5, subscription.getSubPaymentMode());
			preparedStatement.setString(6, subscription.getSubStatus());
			preparedStatement.setString(7, subscription.getSubType());
			preparedStatement.setString(8, subscription.getSubAutoRenewal());
			preparedStatement.setString(9, subscription.getRegId());
			
			int result = preparedStatement.executeUpdate();
			
			if (result>0) {
				//connection.commit();
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
//		finally {
//			dbUtils.closeConnection(connection);
//		}
	}

	@Override
	public String deleteSubscription(String subId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		String deleteStatement = "delete from subscription where subId=?";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, subId);
			
			int result = preparedStatement.executeUpdate();
			
			if(result>0) {
				//connection.commit();
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
//		finally {
//			dbUtils.closeConnection(connection);
//		}
	}

	@Override
	public String updateSubscription(String subId, Subscription subscription) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		String updateStatement = "update subscription"
				+ " set subId = ?, dateOfPayment = ?, expiry = ?, amount = ?, paymentMode = ?,"
				+ " status = ?, type = ?, autoRenewal = ?, regId = ?"
				+ " where (subId = ?)";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, subscription.getSubId());
			preparedStatement.setString(2, subscription.getDateOfPayment());
			preparedStatement.setString(3, subscription.getSubExpiry());
			preparedStatement.setFloat(4, subscription.getSubAmount());
			preparedStatement.setString(5, subscription.getSubPaymentMode());
			preparedStatement.setString(6, subscription.getSubStatus());
			preparedStatement.setString(7, subscription.getSubType());
			preparedStatement.setString(8, subscription.getSubAutoRenewal());
			preparedStatement.setString(9, subscription.getRegId());
			preparedStatement.setString(10, subId);
			
			int result = preparedStatement.executeUpdate();
			
			if (result>0) {
				//connection.commit();
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
//		finally {
//			dbUtils.closeConnection(connection);
//		}
	}

	@Override
	public Optional<Subscription> getSubscriptionById(String subId) throws IdNotFoundException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		String selectStatement = "select * from subscription where subId=?";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, subId);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Subscription subscription = new Subscription();
				subscription.setId(resultSet.getString("subId"));
				subscription.setDateOfPayment(resultSet.getString("dateOfPayment"));
				subscription.setSubExpiry(resultSet.getString("expiryDate"));
				subscription.setSubAmount(resultSet.getInt("amount"));
				subscription.setSubPaymentMode(resultSet.getString("paymentMode"));
				subscription.setSubStatus(resultSet.getString("status"));
				subscription.setSubType(resultSet.getString("subType"));
				subscription.setSubAutoRenewal(resultSet.getString("autoRenewal"));
				subscription.setRegId(resultSet.getString("regId"));
				return Optional.of(subscription);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally {
//			dbUtils.closeConnection(connection);
//		}
		return Optional.empty();
	}

	@Override
	public Subscription[] getAllSubscriptions() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		Optional<List<Subscription>> optional = getAllSubscriptionDetails();
		if(optional.isEmpty())
			return null;
		else {
			List<Subscription> list = optional.get();
			Subscription[] subscriptions = new Subscription[list.size()];
			return list.toArray(subscriptions);
		}

	}

	@Override
	public Optional<List<Subscription>> getAllSubscriptionDetails() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<Subscription> arrayList = new ArrayList<Subscription>();
		
		String selectStatement = "select * from subscription";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Subscription subscription = new Subscription();
				subscription.setId(resultSet.getString("subId"));
				subscription.setDateOfPayment(resultSet.getString("dateOfPayment"));
				subscription.setSubExpiry(resultSet.getString("expiryDate"));
				subscription.setSubAmount(resultSet.getInt("amount"));
				subscription.setSubPaymentMode(resultSet.getString("paymentMode"));
				subscription.setSubStatus(resultSet.getString("status"));
				subscription.setSubType(resultSet.getString("subType"));
				subscription.setSubAutoRenewal(resultSet.getString("autoRenewal"));
				subscription.setRegId(resultSet.getString("regId"));
				arrayList.add(subscription);
			}
			return Optional.ofNullable(arrayList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally {
//			dbUtils.closeConnection(connection);
//		}
		return Optional.empty();
	}

//	private List<Subscription> set = new ArrayList<>();
//	
//	private SubscriptionRepositoryImpl() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	private static SubscriptionRepository subscriptionRepository;
//	
//	public static SubscriptionRepository getInstance() {
//		if(subscriptionRepository == null)
//			subscriptionRepository = new SubscriptionRepositoryImpl();
//		return subscriptionRepository;
//	}
//	
//	
//	@Override
//	public String addSubscription(Subscription subscription) {
//		// TODO Auto-generated method stub
//		boolean result = this.set.add(subscription);
//		if(result)
//			return "Added the Subscription";
//		return "Could'nt add the subscription";
//	}
//
//	@Override
//	public String deleteSubscription(String subId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Subscription> optional = this.getSubscriptionById(subId);
//		if(optional.isPresent()) {
//			boolean result = set.remove(optional.get());
//			if(result) {
//				return "Removed the Subscription of : " + subId;
//			} else {
//				return "Could not delete the subscription something went wrong";
//			}
//		}
//		return "Could not find the subscription Id :" + subId;
//	}
//
//	@Override
//	public String updateSubscription(String subId, Subscription subscription) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Subscription> optional = this.getSubscriptionById(subId);
//		if(optional.isPresent()) {
//			boolean result = set.remove(optional.get());
//			set.add(subscription);
//			if(result) {
//				return "Update the Subscription successfully";
//			} else {
//				return "Could not update the subscription";
//			}
//		}
//		return "Could not find the subscription Id";
//	}
//	
//	@Override
//	public Optional<Subscription> getSubscriptionById(String subId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Subscription subscription2 = null;
//		for (Subscription subscription : set) {
//			if(subscription.getSubId().equals(subId)) {
//				subscription2 = subscription;
//				break;
//			}
//		}
//		return Optional.of(
//				Optional.ofNullable(subscription2)
//				.orElseThrow(()-> new IdNotFoundException("Subscription Id could not be found")));
//	}
//
//	@Override
//	public List<Subscription> getAllSubscriptions() {
//		// TODO Auto-generated method stub
//		List<Subscription> arrayList = new ArrayList<>(set);
//		Collections.sort(arrayList);
//		return arrayList;
//	}


	





}
