package com.zee.zee5app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.naming.InvalidNameException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.zee.zee5app.dto.EROLE;
import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Role;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.RoleRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.EpisodeService;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.RoleService;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.UserService;

@SpringBootApplication
public class Zee5appspringboot2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringboot2Application.class,
				args);

//		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
//		RoleService roleService = applicationContext.getBean(RoleService.class);
//		UserService userService = applicationContext.getBean(UserService.class);
//		RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);
//		MovieService movieService = applicationContext.getBean(MovieService.class);
		
		
		
		//============================= ROLE AND REGISTER========================
//		Set<Role> roles = new HashSet<Role>();
//		
//		
//		for(int i=1;i<=5;++i) {
//			Register register = null;
//			try {
//				Role role = new Role();
//				role.setRoleName(EROLE.ROLE_ADMIN);
//				
//				Role role2 = new Role();
//				role2.setRoleName(EROLE.ROLE_ADMIN);
//				
//				//This was the reason we were getting error of no value present
//				//We need to add the role first or else there would be no 
//				//value present at all
//				
//				roleService.addRole(role);
//				roleService.addRole(role2);
//				
//				register = new Register("reg000" + i, "A" + i, "B" + i, "xyz" + i + "@abc.com", "hello123" + i, new BigDecimal("99797979766"), null , null);
//				roles.add(roleRepository.findById(1).get());
//				roles.add(roleRepository.findById(2).get());
//				register.setRoles(roles);
//				System.out.println(userService.addUser(register));
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println(roles);
		
		//============================ SERIES AND EPISODES ========================
		
//		SeriesService seriesService = applicationContext.getBean(SeriesService.class);
//		Series series = new Series("ser0001", "SeriesName 1", 6, null, "Cast 1", "Genre 1", "2022-04-01", "Language 1", 19, null);
//		Series series2 = new Series("ser0002", "SeriesName 2", 7, null, "Cast 2", "Genre 2", "2022-04-02", "Language 2", 20, null);
//		System.out.println(seriesService.addSeries(series));
//		System.out.println(seriesService.addSeries(series2));
//		Episode episode = new Episode("epi0001", "EpisodeName 1", 20, "Link 1", null, series);
//		EpisodeService episodeService = applicationContext.getBean(EpisodeService.class);
//		System.out.println(episodeService.addEpisode(episode));
		
		
		//=========================== SUBSCRIPTION AND REGISTER ===================
		
//		SubscriptionService subscriptionService = applicationContext.getBean(SubscriptionService.class);
//		Subscription subscription = new Subscription("sub0001", "2022-03-01", "2023-03-01", 2001, "credit1", "yearly1", "active1", "true1", null);
//		Register register = new Register();
//		register.setId("reg0001");
//		subscription.setRegister(register);
//		System.out.println(subscriptionService.addSubscription(subscription));
//		
//		
//		Subscription subscription2 = new Subscription("sub0002", "2022-03-02", "2023-03-02", 2002, "credit2", "yearly2", "active2", "true2", null);
//		Register register2 = new Register();
//		register.setId("reg0001");
//		subscription.setRegister(register2);
//		System.out.println(subscriptionService.addSubscription(subscription2));
		
		//================================== MOVIE ===========================
//		Movie movie = new Movie();
//		movie.setMovieId("mov001");
//		movie.setAgeLimit(18);
//		movie.setCast("Virat Kohli");
//		movie.setMovieLanguage("Kannada");
//		movie.setMovieGenre("Cricket");
//		movie.setMovieName("Life");
//		movie.setMovieLength(180);
//		movie.setMovieReleaseDate("2020-11-05");
//		
//		
//		// ======================== USING BYTE[] FILEINPUT AND FILEOUTPUT=================
//		FileInputStream fileInputStream = null;
//		FileOutputStream fileOutputStream = null;
//		try {
//			fileInputStream = new FileInputStream("C:\\Users\\prajwal.hardekar\\Downloads\\Virat.mp4");
//			File file = new File("C:\\Users\\prajwal.hardekar\\Downloads\\Virat.mp4");
//			//long fileSize = new File("C:\\Users\\prajwal.hardekar\\Downloads\\Virat.mp4").length();
//			long fileSize = file.length();
//			byte[] allBytes = new byte[(int) fileSize];
//			fileInputStream.read(allBytes);
//			
//			movie.setMovieTrailer("C:\\Users\\prajwal.hardekar\\Downloads\\Virat.mp4");
//			
//			String result = movieService.addMovie(movie);
//			
//			if(result.equals("Successful")) {
//				fileOutputStream = new FileOutputStream("C:\\Users\\prajwal.hardekar\\Downloads\\MovieStore\\" + file.getName());
//				
//				byte[] data = new byte[(int)file.length()];
//				
//				fileInputStream.read(data);
//				fileOutputStream.write(data);
//							
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				fileInputStream.close();
//				fileOutputStream.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
//		
//		FileOutputStream fileOutputStream = null;
//		try {
//			Optional<Movie> optional = movieService.getMovieById("mov001");
//			if(optional.isEmpty()) {
//				System.out.println("Record Not Found");
//			} else {
//				//We should print the info and copy the file to movies folder with name virat2
//				Movie movie = optional.get();
//				fileOutputStream = new FileOutputStream("C:\\Users\\prajwal.hardekar\\Downloads\\read\\virat2.mp4");
//				fileOutputStream.write(movie.getMovieTrailer());
//				
//			}
//		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				fileOutputStream.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		
		
		
		
		
		//System.out.println(userRepository.existsByEmailAndContactNumber("xyz1@abc.com" , new BigDecimal(91917979797.00)));
		//applicationContext.close();
	}

}
