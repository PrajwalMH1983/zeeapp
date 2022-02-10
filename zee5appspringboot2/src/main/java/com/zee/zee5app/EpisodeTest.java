package com.zee.zee5app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.service.EpisodeService;

@SpringBootApplication
public class EpisodeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext applicationContext = SpringApplication.run(EpisodeTest.class, args);
		
		EpisodeService episodeService = applicationContext.getBean(EpisodeService.class);
		
		
		
		
		System.out.println("ADDING EPISODE :\n");
		
		for(int i=1;i<=5;++i) {
			Episode episode = new Episode("epi000" + i , "ser000" + i , "episodeName " + i , 30 + i , "Link : " + i , null);
			System.out.println(episodeService.addEpisode(episode) + " " + i);
		}
		
		System.out.println();
		
		
		
		
		
		System.out.println("GET EPISODE BY ID : \n");
		
		try {
			System.out.println(episodeService.getEpisodeById("epi0003").get());
		} catch (IdNotFoundException | InvalidIdLengthException | InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		
		
		System.out.println("GET ALL EPISODE DETAILS IN LIST :\n ");
		try {
			episodeService.getAllEpisodeDetails().get().forEach(e->System.out.println(e));
		} catch (InvalidIdLengthException | InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		
		
		
		System.out.println("DELETE EPISODE BY ID : \n");
		
		try {
			System.out.println(episodeService.deleteEpisode("epi0004"));
		} catch (IdNotFoundException | InvalidIdLengthException | InvalidNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println();
		
		
		System.out.println("GET ALL EPISODE DETAILS IN ARRAY : \n");
		
		for (Episode episode : episodeService.getAllEpisodes()) {
			System.out.println(episode);
		}
		
		System.out.println();
		
		applicationContext.close();
		
		
		
	}

}
