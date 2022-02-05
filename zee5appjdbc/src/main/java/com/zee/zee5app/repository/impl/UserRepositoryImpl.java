package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.management.loading.PrivateClassLoader;
import javax.sql.DataSource;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;

public class UserRepositoryImpl implements UserRepository {
	
	//DBUtils dbUtils = DBUtils.getInstance();
	LoginRepository loginRepository = LoginRepositoryImpl.getInstance();
	
	DBUtils dbUtils = DBUtils.getInstance();
	
	private static UserRepositoryImpl repository;
	
	private UserRepositoryImpl() throws IOException{
		
	}
	
	
	//private static UserRepository repository;
	
	public static UserRepositoryImpl getInstance() throws IOException {
		if(repository == null)
			repository = new UserRepositoryImpl();
		return repository;
	}

	
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		
		//For connection object
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		//Add the user details to the table in DB
		String insertStatement = "insert into register" 
				+ "(regId , firstName , lastName , email , contactNumber , password)" 
				+ "values(? , ? , ? , ? , ? , ?)";
		
		//Placeholders --, prepared statement
		
		//We will concatenate the values in values spec
		// we will use a (?)
		//here we will provide the values against ? (placeholder)
		//In both of the above situations data will be added at runtime itself
		

		
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			//We need to provide values against ? placeholder
			//we are taking setString cuz regId is a string based column
			preparedStatement.setString(1, register.getId());
			preparedStatement.setString(2, register.getFirstName());
			preparedStatement.setString(3, register.getLastName());
			preparedStatement.setString(4, register.getEmail());
			preparedStatement.setBigDecimal(5, register.getContactNumber());
			String salt = PasswordUtils.getSalt(30);
			String encryptedPassword = PasswordUtils.generateSecurePassword(register.getPassword(), salt);
			preparedStatement.setString(6, encryptedPassword);
			
			//The number of rows affected by the DML statement
			//1 : one row is inserted
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				//connection.commit();
				Login login = new Login();
				login.setUserName(register.getEmail());
				login.setPassword(encryptedPassword);
				login.setRegId(register.getId());
				login.setRole(ROLE.ROLE_USER);
				String res = loginRepository.addCredentials(login);
				if(res.equals("Successful")) {
					//connection.commit();
					return "Successful";
				} else {
					connection.rollback();
					return "Failed";
				}
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
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			return "Failed";
		}
		finally {
			//Closure work is done in finally block
			//without fail we have to close the connection 
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public String updateUser(String userId, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection;
		PreparedStatement preparedStatement;
		
		String updateStatement = "update register"
				+ " set regId = ?, firstName = ?, lastName = ?, email = ?, contactNumber = ?, password = ?"
				+ " where (regId = ?)";
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			//We need to provide values against ? placeholder
			//we are taking setString cuz regId is a string based column
			preparedStatement.setString(1, register.getId());
			preparedStatement.setString(2, register.getFirstName());
			preparedStatement.setString(3, register.getLastName());
			preparedStatement.setString(4, register.getEmail());
			preparedStatement.setBigDecimal(5, register.getContactNumber());
			String salt = PasswordUtils.getSalt(30);
			String encryptedPassword = PasswordUtils.generateSecurePassword(register.getPassword(), salt);
			preparedStatement.setString(6, encryptedPassword);
			
			preparedStatement.setString(7, userId);
			
			int result = preparedStatement.executeUpdate();
			
			if (result>0) {
				Login login = new Login();
				login.setUserName(register.getEmail());
				login.setPassword(encryptedPassword);
				login.setRegId(register.getId());
				login.setRole(ROLE.ROLE_USER);
				String res = loginRepository.updateCredentials(register.getId(), login);
				if(res.equals("Successful"))
					return "Successful";
				else {
					connection.rollback();
					return "Failed";
				}
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
		finally {
			dbUtils.closeConnection(connection);
		}
		
	}

	@Override
	public Optional<Register> getUserById(String userId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String selecStatement = "select * from register where regId=?";
//		try {
//			connection = dataSource.getConnection();
//		} catch (SQLException e) {
//			// TODO: handle exception
//		}
		
		
		try {
			preparedStatement = connection.prepareStatement(selecStatement);
			preparedStatement.setString(1, userId);
			
			//To retrieve the data
			//ResultSet  will help u hold the complete result
			//ResultSet object its a single one which is holding all the records
			//so we need to to traverse it 
			
			resultSet = preparedStatement.executeQuery();
			//We wont be using while cuz at max we would be getting 1 record so
			if(resultSet.next()) {
				//next method is used to traverse the resultSet
				//initially it will be placed just above the 1st record
				//when u will call 1st time RS will retrieve the 1st record
				//and it will refer the 2nd one
				//Now we have to collect the information and to collect this info
				Register register = new Register();
				register.setId(resultSet.getString("regId"));
				register.setFirstName(resultSet.getString("firstName"));
				register.setLastName(resultSet.getString("lastName"));
				register.setEmail(resultSet.getString("email"));
				register.setPassword(resultSet.getString("password"));
				register.setContactNumber(resultSet.getBigDecimal("contactNumber"));
				
				return Optional.of(register);
			}
			
			resultSet = preparedStatement.executeQuery();
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
	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		Optional<List<Register>> optional = getAllUsersDetails();
		
		if(optional.isEmpty()) {
			return null;
		} else {
			List<Register> list = optional.get();
			Register[] registers = new Register[list.size()];
			return list.toArray(registers);
		}
	}

	
	
	
	@Override
	public Optional<List<Register>> getAllUsersDetails() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		ArrayList<Register> arrayList = new ArrayList<>();
		
		String selecStatement = "select * from register";
		connection = dbUtils.getConnection();
		
		
		try {
			preparedStatement = connection.prepareStatement(selecStatement);
			//preparedStatement.setString(1, userId);
			
			//To retrieve the data
			//ResultSet  will help u hold the complete result
			//ResultSet object its a single one which is holding all the records
			//so we need to to traverse it 
			
			resultSet = preparedStatement.executeQuery();
			//We wont be using while cuz at max we would be getting 1 record so
			while(resultSet.next()) {
				//next method is used to traverse the resultSet
				//initially it will be placed just above the 1st record
				//when u will call 1st time RS will retrieve the 1st record
				//and it will refer the 2nd one
				//Now we have to collect the information and to collect this info
				Register register = new Register();
				register.setId(resultSet.getString("regId"));
				register.setFirstName(resultSet.getString("firstName"));
				register.setLastName(resultSet.getString("lastName"));
				register.setEmail(resultSet.getString("email"));
				register.setPassword(resultSet.getString("password"));
				register.setContactNumber(resultSet.getBigDecimal("contactNumber"));
				
				arrayList.add(register);
			}
			return Optional.ofNullable(arrayList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	
	
	
	
	@Override
	public String deleteUserById(String userId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		//Add the user details to the table in DB
		String deleteStatement = "delete from register where regId=?" ;
		
		//Placeholders --, prepared statement
		
		//We will concatenate the values in values spec
		// we will use a (?)
		//here we will provide the values against ? (placeholder)
		//In both of the above situations data will be added at runtime itself
		
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			//We need to provide values against ? placeholder
			//we are taking setString cuz regId is a string based column
			preparedStatement.setString(1, userId);
			
			
			//The number of rows affected by the DML statement
			//1 : one row is inserted
			int result = preparedStatement.executeUpdate();
			//Gives us the number of rows deleted
			if(result > 0) {
				Register register = new Register();
				String ans = loginRepository.deleteCredentials(register.getEmail());
				if(ans == "Successful")
					return "Successful";
			}
			else
				return "Failed";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		}
		finally {
			//Closure work is done in finally block
			//without fail we have to close the connection 
			dbUtils.closeConnection(connection);
		}
		return "Failed";
	}


	
	
	
	
//===========================================================================================
	
	
	

//	//We need singleton object for Repository
//	//As list is parent for linked list
//	//A parent reference can handle the object of child
//	//ArrayList and LinkedList both are referring to the same parent
//	
//	//private List<Register> arrayList = new LinkedList<>();
//	private TreeSet<Register> set = new TreeSet<>();
//	//private Set<Register> set = new LinkedHashSet<>();
//	//HashSet by default holds 16 elements
//	private Set<Register> arrayList = new HashSet<>();
//	
//	
//	//When u will use DC for AL then by default it will hold 10 elements
//	//of type Register 
//	//private Register[] registers = new Register[10];
//	private static int count = -1;
//	
//	
//	private UserRepositoryImpl(){
//		
//	}
//	
//	
//	@Override
//	public String addUser(Register register) {
//		// TODO Auto-generated method stub
//		//registers.length ===> gives us the availability
////		if(count == registers.length - 1) {
////			//array is full or we should go for dynamically growing the size of array
////			Register temp[] = new Register[registers.length * 2];
////			
////			//We need to copy the contents from old to new
////			System.arraycopy(registers, 0, temp, 0 , registers.length);
////			
////			registers = temp;
////			
////			//We were not adding the newly created object 
////			//So the provided object is added into the array or not
////			registers[++count] = register;
////			
////			return "Success";
////		}
////		registers[++count] = register;
////		return "Success";
//		//return null;
//		
//		
//		boolean result = this.arrayList.add(register);
//		System.out.println(this.arrayList.size());
//		if(result)
//			return "Added a User";
//		
//		return "Could'nt add ";
//	}
//
//	@Override
//	public String updateUser(String userId, Register register) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Register> optional = this.getUserById(userId);
//		if(optional.isPresent()) {
//			boolean result = set.remove(optional.get());
//			set.add(register);
//			if(result) {
//				return "Updated User";
//			} else {
//				return "Could'nt update User";
//			}
//		}
//		return "Failed To Update User";
//	}
//
//	@Override
//	public Optional<Register> getUserById(String userId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Register register2 = null;
//		for (Register register : arrayList) {
//			if(register.getId().equals(userId)) {
//				register2 = register;
//				break;
//			}
//		}
//		return Optional.of(Optional.ofNullable(register2)
//				.orElseThrow(()-> new IdNotFoundException("Id Not Found Message")));
//	}
//	
////	@Override
////	//Optional its a class which is specifically designed for handling the NullPointerException
////	public Register getUserById(String userId) throws IdNotFoundException{// throws IdNotFoundException {
////		// TODO Auto-generated method stub
////		//We need to traverse the AL 
////		Register register2 = null;
////		for (Register register : arrayList) {
////			if(register != null && register.getId().equals(userId)) {
////				//return Optional.of(register);	//We use Optional.of() only when we are sure that our program is gonna return something
////				register2 = register;
////			}
////
////		}
////		if(register2 == null) {
////			throw new IdNotFoundException("Id Not Found");
////		} else {
////			return register2;
////		}
////		//return Optional.empty();
////		
//////		Register register2 = null;
//////		for (Register register : arrayList) {
//////			if(register != null && register.getId().equals(userId)) {
//////				register2 = register;
//////			}
//////
//////		}
////		
////		//If register2 is holding null it will act like an empty 
////		//If register2 is holding object it will act like of
//////		return Optional.ofNullable(Optional
//////				.of(register2)
//////				.orElseThrow(()->new IdNotFoundException("Id Not Found")));
////	}
//
//	@Override
//	public Register[] getAllUsers() {
//		// TODO Auto-generated method stub
//		//return registers;
//		Register register[] = new Register[arrayList.size()];
//		return arrayList.toArray(register);
//	}
//
//	@Override
//	public String deleteUserById(String userId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Register> optional = this.getUserById(userId);
//		
//		if(optional.isPresent()) {
//			//Removal
//			boolean result = arrayList.remove(optional.get());
//			if(result) {
//				return "Sucessful";
//			} else {
//				return "Failure";
//			}
//		} else {
//			//Throw IdNotFoundException 
//			throw new IdNotFoundException("Id Not Found Exception");
//		}
//	}
//	
//	@Override
//	public List<Register> getAllUsersDetails() {
//		// TODO Auto-generated method stub
//		//We are returning all the details but we need to return it in terms of sorted order
//		//To convert the set to list
//		//Collections.sort(arrayList);
//		//return arrayList;
//		
//		return new ArrayList<>(set.descendingSet());
//	}
//	

}
