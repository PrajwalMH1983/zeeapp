package com.zee.zee5app.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.zee.zee5app.security.services.UserDetailsImpl;

//To provide the tokens
@Component
//Should be responsible for generating the tokens and validating the tokens
public class JwtUtils {
	
	//will generate the logs automatically
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
	
	//read JwtSecret
	//To read the yml specificatons
	@Value("${zee5app.app.jwtSecret}")
	private String jwtSecret;
	
	//read expiry value
	@Value("${zee5app.app.jwtExpirationMs}")
	private int jwtExpiryValue;
	
	
//	public String generateToken(Authentication authentication) {
//		//retrieve userName 
//		//issued @ when token is generated
//		//expiry
//		//encrypted strategy -- ex SHA512
//		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();	//getPrincipal will hold ur user details
//		//userName
//		
//	}
}
