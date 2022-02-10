package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface SubscriptionService {
	public String addSubscription(Subscription subscription);
	public String deleteSubscription(String subId) throws IdNotFoundException, InvalidIdLengthException;
	public Optional<Subscription> getSubscriptionById(String subId) throws IdNotFoundException, InvalidIdLengthException;
	public Subscription[] getAllSubscriptions() throws InvalidIdLengthException;
	public Optional<List<Subscription>> getAllSubscriptionDetails() throws InvalidIdLengthException;
}
