package com.zee.zee5app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.payload.response.MessageResponse;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.security.services.UserDetailsImpl;
import com.zee.zee5app.service.SubscriptionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@PostMapping("addSubscription")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addSubscription(@Valid @RequestBody Subscription subscription) {
		if(subscriptionRepository.existsById(subscription.getSubId())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error : Subscription Id Already Exists"));
		}
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Long id = userDetailsImpl.getId();
		Optional<User> optional = userRepository.findById(id);
		subscription.setUser(optional.get());
		subscriptionRepository.save(subscription);
		
		return ResponseEntity
				.status(201)
				.body(new MessageResponse("Subscription insertion was successful"));
	}
	
	@GetMapping("/all")
	@PreAuthorize(" hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllSubscriptions() throws InvalidIdLengthException {
		Optional<List<Subscription>> optional = subscriptionService.getAllSubscriptionDetails();
		if(optional.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.body(new MessageResponse("No Record Found"));
		}
		return ResponseEntity.ok(optional.get());
	}
}
