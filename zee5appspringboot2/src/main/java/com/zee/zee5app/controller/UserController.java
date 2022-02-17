package com.zee.zee5app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.EROLE;
import com.zee.zee5app.dto.Role;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.payload.request.LoginRequest;
import com.zee.zee5app.payload.request.SignUpRequest;
import com.zee.zee5app.payload.response.JwtResponse;
import com.zee.zee5app.payload.response.MessageResponse;
import com.zee.zee5app.repository.RoleRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.security.jwt.JwtUtils;
import com.zee.zee5app.security.services.UserDetailsImpl;
import com.zee.zee5app.service.UserService;

@CrossOrigin("*")
@RestController	//Version 4 @ResponseBody @Controller
//When ur target is to deal with the REST Api
//then u have to set the RESPONSEand wherever we have to share the response that method
//must be marked with @ResponseBody
//If the class is having 1000 methods then we have to mark 1000 times
//to avoid this they merged controller with responseBody to form RestController

@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
						, loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateToken(authentication);
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		//getAuthorities() ---> will help u to get the authority details , will give u user related details
		//then we are converting it into stream 
		//and then traversing the stream and then retrieving the authority value 
		//then forming the list of type string 
		List<String> roles = userDetailsImpl.getAuthorities()
				.stream()
				.map(i->i.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
				userDetailsImpl.getId(),
				userDetailsImpl.getUsername(),
				userDetailsImpl.getEmail(), 
				roles));
	}
	
	
	
	
	
	//we are not following the older one cuz there we have relation between the role table and the
	//user table and it increases the payload size as well
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		
		if(userRepository.existsByUserName(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error : UserName is already taken !"));
		}
		
		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error : Email is already taken !"));
		}
		
		
		
		
		//User's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword()),
				signUpRequest.getFirstName(),
				signUpRequest.getLastName());
		
		
		
		
		//retrieving the roles details 
		Set<String> strRoles = signUpRequest.getRole();
		
//		for (String role : strRoles) {
//			System.out.println(role);
//		}
		
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null) {
			Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER)
					.orElseThrow(()->new RuntimeException("Error : Role not found 0"));
			roles.add(userRole);
		}
		else {
			strRoles.forEach(e -> {
				switch (e) {
				case "admin":
					Role roleAdmin = roleRepository.findByRoleName(EROLE.ROLE_ADMIN)
						.orElseThrow(()-> new RuntimeException("Error : Role Not Found 1"));
					roles.add(roleAdmin);
					break;
					
				case "mod":
					Role roleMod = roleRepository.findByRoleName(EROLE.ROLE_MODERATOR)
							.orElseThrow(()-> new RuntimeException("Error : Role Not Found 2"));
						roles.add(roleMod);
					break;

				default:
					Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER)
						.orElseThrow(()->new RuntimeException("Error : Role not found 3"));
					roles.add(userRole);
					break;
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		
		return ResponseEntity.status(201).body(new MessageResponse("User created Successfully"));
	}
	
	
	
	
	
	
	//responsible for adding the information
	//Info will be shared by the client in terms of JSON object
	//we need to read that JSON object
	//This JSON object is available in RequestBody
	//RequestBody is responsible for storing the information provided
	//We need to read that requestBody 
	//We need to transform JSON Object to java object ====> will be done by RequestBody
	
	
	//We need to inform that when this method should be called
	//for that we have to specify the endpoint
	
	
	//Here so now it will throw exception to spring so it will handle that now
	//We use @Valid because it would be better to validate the data before getting it into the DB rather than validating at the DB
//	@PostMapping("/addUser")
//	public ResponseEntity<?> addUser(@Valid @RequestBody User register) throws AlreadyExistsException {
//		
//		//These things are expected for each and every REST API calls
//		//1. It should store the received info into DB
////		try {
//			User result = userService.addUser(register);
//			System.out.println(result);
//			return ResponseEntity.status(201).body(result);
			
//		} catch (AlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			//Return a json object with message : Record already exists
//			//Status : Problem
//			
//			Map<String, String> hashMap = new HashMap<>();
//			
//			hashMap.put("message", "Record Already exists");
//			
//			//Intead of providing the status(400) we have provided badRequest()
//			return ResponseEntity.badRequest().body(hashMap);
//		}
		//2. validation
		//3. return the crispy info to the client
		//4. customization in error response
		//5.declaration of custom exception

		
		//here we are supposed to return a json object with status problem 
//	}
	
	
	
	//1. Retrieve a specific record
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws IdNotFoundException{
//		User register = userService.getUserById(id);
//		return ResponseEntity.ok(register);
//	}
	
	//2. Retrieving all records
//	@GetMapping("/all")
////	@PreAuthorize
////	@PostAuthorize
//	public ResponseEntity<?> getAllUsersDetails() {
//		Optional<List<User>> optional = userService.getAllUsersDetails();
//		if(optional.isEmpty()) {
////			Map<String, String> map = new HashMap<>();
////			map.put("message" , "No Record Found");
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("No Record Found"));
//		}
//		return ResponseEntity.ok(optional.get());
//	}
	
	//3. page wise records
}
