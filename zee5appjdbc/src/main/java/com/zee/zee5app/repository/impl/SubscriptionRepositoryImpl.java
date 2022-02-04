package com.zee.zee5app.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SubscriptionRepository;

public class SubscriptionRepositoryImpl implements SubscriptionRepository {

	private List<Subscription> set = new ArrayList<>();
	
	private SubscriptionRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static SubscriptionRepository subscriptionRepository;
	
	public static SubscriptionRepository getInstance() {
		if(subscriptionRepository == null)
			subscriptionRepository = new SubscriptionRepositoryImpl();
		return subscriptionRepository;
	}
	
	
	@Override
	public String addSubscription(Subscription subscription) {
		// TODO Auto-generated method stub
		boolean result = this.set.add(subscription);
		if(result)
			return "Added the Subscription";
		return "Could'nt add the subscription";
	}

	@Override
	public String deleteSubscription(String subId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Subscription> optional = this.getSubscriptionById(subId);
		if(optional.isPresent()) {
			boolean result = set.remove(optional.get());
			if(result) {
				return "Removed the Subscription of : " + subId;
			} else {
				return "Could not delete the subscription something went wrong";
			}
		}
		return "Could not find the subscription Id :" + subId;
	}

	@Override
	public String updateSubscription(String subId, Subscription subscription) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Subscription> optional = this.getSubscriptionById(subId);
		if(optional.isPresent()) {
			boolean result = set.remove(optional.get());
			set.add(subscription);
			if(result) {
				return "Update the Subscription successfully";
			} else {
				return "Could not update the subscription";
			}
		}
		return "Could not find the subscription Id";
	}
	
	@Override
	public Optional<Subscription> getSubscriptionById(String subId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Subscription subscription2 = null;
		for (Subscription subscription : set) {
			if(subscription.getSubId().equals(subId)) {
				subscription2 = subscription;
				break;
			}
		}
		return Optional.of(
				Optional.ofNullable(subscription2)
				.orElseThrow(()-> new IdNotFoundException("Subscription Id could not be found")));
	}

	@Override
	public List<Subscription> getAllSubscriptions() {
		// TODO Auto-generated method stub
		List<Subscription> arrayList = new ArrayList<>(set);
		Collections.sort(arrayList);
		return arrayList;
	}


	





}
