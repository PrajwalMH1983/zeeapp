package com.zee.zee5app.dto.service;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.repository.SubscriptionRepository;

public class SubscriptionService {
	private SubscriptionRepository repository = SubscriptionRepository.getInstance();

	private SubscriptionService() {
		
	}
	
	static SubscriptionService service = null;
	
	public static SubscriptionService getInstance() {
		if(service == null)
			service = new SubscriptionService();
		
		return service;
	}
	
	public String addSubscription(Subscription subscription) {
		return this.repository.addSubscription(subscription);
	}
	
	public Subscription getSubscriptionById(String subId) {
		return repository.getSubscriptionById(subId);
	}
	
	public Subscription[] getSubscriptions() {
		return repository.getSubscriptions();
	}
	
	public String  updateSubscription(String subId , Subscription subscription) {
		return repository.updateSubscription(subId, subscription);
	}
	
	public String deleteSubscription(String subId) {
		return repository.deleteSubscription(subId);
	}
}
