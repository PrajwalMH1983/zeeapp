package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.impl.SubscriptionRepositoryImpl;
import com.zee.zee5app.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	private SubscriptionRepository subscriptionRepository;// = SubscriptionRepositoryImpl.getInstance();
	private SubscriptionServiceImpl() throws IOException {
		// TODO Auto-generated constructor stub
	}
	
	private static SubscriptionService subscriptionService;
//	public static SubscriptionService getInstance() throws IOException {
//		if(subscriptionService==null)
//			subscriptionService = new SubscriptionServiceImpl();
//		return subscriptionService;
//	}
	
	@Override
	public String addSubscription(Subscription subscription) {
		// TODO Auto-generated method stub
		return subscriptionRepository.addSubscription(subscription);
	}

	@Override
	public String deleteSubscription(String subId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return subscriptionRepository.deleteSubscription(subId);
	}

	@Override
	public String updateSubscription(String subId, Subscription subscription) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return subscriptionRepository.updateSubscription(subId, subscription);
	}

	@Override
	public Optional<Subscription> getSubscriptionById(String subId) throws IdNotFoundException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return subscriptionRepository.getSubscriptionById(subId);
	}

	@Override
	public Subscription[] getAllSubscriptions() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return subscriptionRepository.getAllSubscriptions();
	}

	@Override
	public Optional<List<Subscription>> getAllSubscriptionDetails() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return subscriptionRepository.getAllSubscriptionDetails();
	}
	
	

//	private SubscriptionRepository repository = SubscriptionRepositoryImpl.getInstance();
//	private static SubscriptionService service;
//	
//	public static SubscriptionService getInstance() {
//		if(service == null)
//			service = new SubscriptionServiceImpl();
//		return service;
//	}
//	
//	private SubscriptionServiceImpl() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//	@Override
//	public String addSubscription(Subscription subscription) {
//		// TODO Auto-generated method stub
//		return this.repository.addSubscription(subscription);
//	}
//
//	@Override
//	public String deleteSubscription(String subId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.repository.deleteSubscription(subId);
//	}
//
//	@Override
//	public String updateSubscription(String subId, Subscription subscription) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.repository.updateSubscription(subId, subscription);
//	}
//	
//	@Override
//	public Optional<Subscription> getSubscriptionById(String subId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.repository.getSubscriptionById(subId);
//	}
//
//	@Override
//	public List<Subscription> getAllSubscriptions() {
//		// TODO Auto-generated method stub
//		return this.repository.getAllSubscriptions();
//	}



	

}
