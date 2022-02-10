package com.zee.zee5app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.service.SeriesService;

@SpringBootApplication
public class SeriesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(SeriesTest.class, args);
		
		SeriesService seriesService = applicationContext.getBean(SeriesService.class);
		System.out.println("Add Series");
		for (int i = 1; i <= 5; i++) {
			Series series = new Series("ser000"+i, "SeriesName "+i, "Genre "+i , "2022-04-0"+i, "Language"+i ,  "Cast"+i , 5+i , 18+i , null);
			System.out.println(seriesService.addSeries(series) + " " + i);
		}
		System.out.println();
		
		System.out.println("GET SERIES BY ID :");
		try {
			System.out.println(seriesService.getSeriesById("ser0004").get());
		} catch (IdNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println();
		
		System.out.println("GET ALL SERIES DETAILS IN LIST");
		seriesService.getAllSeriesDetails().get().forEach(e->System.out.println(e));
		System.out.println();
		
		System.out.println("DELETE SERIES BY ID :");
		try {
			System.out.println(seriesService.deleteSeries("ser0003"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println();
		
		System.out.println("GET ALL SERIES DETAILS IN ARRAY");
		for (Series series : seriesService.getAllSeries()) {
			System.out.println(series);
		}
		System.out.println();
		
		applicationContext.close();
	}

}
