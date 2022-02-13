package com.zee.zee5app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.SubscriptionService;

@SpringBootApplication
public class SubscriptionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(SubscriptionTest.class, args);
		
		SubscriptionService subscriptionService = applicationContext.getBean(SubscriptionService.class);
		System.out.println("ADD SUBSCRIPTION : ");
		for (int i = 1; i <= 5; i++) {
			Subscription subscription = new Subscription("sub000"+i, "2022-03-0"+i, "2023-03-0"+i, 1000+i, "debit "+i, "yearly "+i, "active "+i, "true "+i, null);
			System.out.println(subscriptionService.addSubscription(subscription) + " " + i);
		}
		System.out.println();
		
		System.out.println("GET SUBSCRIPTION BY ID");
		try {
			System.out.println(subscriptionService.getSubscriptionById("sub0004").get());
		} catch (IdNotFoundException | InvalidIdLengthException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println();
		
		System.out.println("GET ALL SUBSCRIPTION DETAILS IN LIST");
		try {
			subscriptionService.getAllSubscriptionDetails().get().forEach(e->System.out.println(e));
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
//		System.out.println("DELETE SUBSCRIPTION BY ID :");
//		try {
//			System.out.println(subscriptionService.deleteSubscription("sub0003"));
//		} catch (IdNotFoundException | InvalidIdLengthException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println();
		
		System.out.println("GET ALL SUBSCRIPTION DETAILS IN ARRAY :");
		try {
			for (Subscription subscription : subscriptionService.getAllSubscriptions()) {
				System.out.println(subscription);
			}
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
		applicationContext.close();
	}

}
