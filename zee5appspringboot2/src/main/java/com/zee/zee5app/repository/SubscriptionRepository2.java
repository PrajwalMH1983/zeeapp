package com.zee.zee5app.repository;

import com.zee.zee5app.dto.Subscription;

public class SubscriptionRepository2 {
	private Subscription[] subscriptions = new Subscription[10];
	private static int count = -1;
	private SubscriptionRepository2() {
		
	}
	
	private static SubscriptionRepository2 subscriptionRepository;
	
	public static SubscriptionRepository2 getInstance() {
		if(subscriptionRepository == null)
			subscriptionRepository = new SubscriptionRepository2();
		return subscriptionRepository;
	}
	
	public String addSubscription(Subscription subscription){ 
		if(count == subscriptions.length - 1) {
			Subscription temp[] = new Subscription[subscriptions.length * 2];
			System.arraycopy(subscriptions, 0 , temp, 0 , subscriptions.length);
			subscriptions = temp;
			subscriptions[++count] = subscription;
			return "Array is Full";
		}
		subscriptions[++count] = subscription;
		return "Success";
	}
	
	
	public Subscription[] getSubscriptions() {
		return subscriptions;
	}
	
	public Subscription getSubscriptionById(String subId) {
		for (Subscription subscription : subscriptions) {
			if(subscription != null && subscription.getSubId().equals(subId)) {
				return subscription;
			}
		}
		return null;
	}
	
	public String updateSubscription(String subId , Subscription subscription1){
		int count1 = 0;
		for (Subscription subscription : subscriptions) {
			if(subscription != null && subscription.getSubId().equals(subId)) {
				subscriptions[count1] = subscription1;
				return "Done";
			}
			++count1;
		}
		return "Not Done";
	}
		
	public String deleteSubscription(String subId) {
		int count1 = 0;
		for (Subscription subscription : subscriptions) {
			if(subscription != null && subscription.getSubId().equals(subId)) {
				System.arraycopy(subscriptions, count1 + 1, subscriptions, count1, subscriptions.length - count1 - 1);
				subscriptions[subscriptions.length - 1] = null;
				return "Done";
			}
			++count1;
		}
		return "Not Done";
	}
}
