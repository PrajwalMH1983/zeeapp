package com.zee.zee5app.dto.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.repository.SubscriptionRepository;
import com.zee.zee5app.dto.repository.SubscriptionRepository2;

public class SubscriptionService2 {
	private SubscriptionRepository2 repository = SubscriptionRepository2.getInstance();

	private SubscriptionService2() {
		
	}
	
	static SubscriptionService2 service = null;
	
	public static SubscriptionService2 getInstance() {
		if(service == null)
			service = new SubscriptionService2();
		
		return service;
	}
	
	public String addSubscription(Subscription subscription) {
		return this.repository.addSubscription(subscription);
	}
	
	public Subscription getSubscriptionById(String subId) {
		return repository.getSubscriptionById(subId);
	}
	
	public Subscription[] getAllSubscriptions() {
		return repository.getSubscriptions();
	}
	
	public String  updateSubscription(String subId , Subscription subscription) {
		return repository.updateSubscription(subId, subscription);
	}
	
	public String deleteSubscription(String subId) {
		return repository.deleteSubscription(subId);
	}
}
