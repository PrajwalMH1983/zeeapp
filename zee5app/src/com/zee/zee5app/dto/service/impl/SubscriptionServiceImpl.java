package com.zee.zee5app.dto.service.impl;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.repository.SubscriptionRepository;
import com.zee.zee5app.dto.repository.impl.SubscriptionRepositoryImpl;
import com.zee.zee5app.dto.service.SubscriptionService;
import com.zee.zee5app.exception.IdNotFoundException;

public class SubscriptionServiceImpl implements SubscriptionService {

	private SubscriptionRepository repository = SubscriptionRepositoryImpl.getInstance();
	private static SubscriptionService service;
	
	public static SubscriptionService getInstance() {
		if(service == null)
			service = new SubscriptionServiceImpl();
		return service;
	}
	
	private SubscriptionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String addSubscription(Subscription subscription) {
		// TODO Auto-generated method stub
		return this.repository.addSubscription(subscription);
	}

	@Override
	public String deleteSubscription(String subId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.deleteSubscription(subId);
	}

	@Override
	public String updateSubscription(String subId, Subscription subscription) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.updateSubscription(subId, subscription);
	}
	
	@Override
	public Optional<Subscription> getSubscriptionById(String subId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.getSubscriptionById(subId);
	}

	@Override
	public List<Subscription> getAllSubscriptions() {
		// TODO Auto-generated method stub
		return this.repository.getAllSubscriptions();
	}



	

}
