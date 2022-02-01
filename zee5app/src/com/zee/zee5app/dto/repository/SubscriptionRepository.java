package com.zee.zee5app.dto.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;

public interface SubscriptionRepository {
	public String addSubscription(Subscription subscription);
	public String deleteSubscription(String subId) throws IdNotFoundException;
	public String updateSubscription(String subId , Subscription subscription) throws IdNotFoundException;
	public Optional<Subscription> getSubscriptionById(String subId) throws IdNotFoundException;
	public List<Subscription> getAllSubscriptions();
}
