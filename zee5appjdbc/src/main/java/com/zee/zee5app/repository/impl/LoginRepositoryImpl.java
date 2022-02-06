package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

	//DBUtils dbUtils = DBUtils.getInstance();
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private LoginRepository loginRepository;
	
	private LoginRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
//	private static LoginRepository repository;
//	
//	public static LoginRepository getInstance() throws IOException {
//		if(repository == null)
//			repository = new LoginRepositoryImpl();
//		return repository;
//	}
	
	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		String insertStatement = "insert into login"
				+ "(userName , password , regId , role)" 
				+ "values(? , ? , ? , ?)";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, login.getUserName());
			
			preparedStatement.setString(2, login.getPassword());
			
			preparedStatement.setString(3, login.getRegId());
			
			preparedStatement.setString(4, login.getRole().toString());
			
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
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
//		 finally {
//			dbUtils.closeConnection(connection);
//		}
		
		
	}

	@Override
	public String deleteCredentials(String userName) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		String deleteStatement = "delete from login where userName = ?";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, userName);
			
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
//		finally {
//			dbUtils.closeConnection(connection);
//		}
	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		String updateStatement = "update login set password = ? where userName = ?";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, userName);
			
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
//		finally {
//			dbUtils.closeConnection(connection);
//		}
	}

	@Override
	public String changeRole(String userName, ROLE role) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		String updateStatement = "update login set role=? where userName=?";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
				preparedStatement = connection.prepareStatement(updateStatement);
				preparedStatement.setString(1, role.toString());
				preparedStatement.setString(2, userName);
				
				int result = preparedStatement.executeUpdate();
				
				if(result > 0) {
					connection.commit();
					return "Successful";
				}else {
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
//		finally {
//				//Closure
//				dbUtils.closeConnection(connection);
//			}
		
	}

	@Override
	public String updateCredentials(String regId, Login login) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		String updateStatement = "update login"
				+ " set userName = ?, password = ?, regId = ?, role = ?"
				+ " where (regId = ?)";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, login.getUserName());
			preparedStatement.setString(2, login.getPassword());
			preparedStatement.setString(3, login.getRegId());
			preparedStatement.setString(4, login.getRole().toString());
			preparedStatement.setString(5, regId);
			
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



}
