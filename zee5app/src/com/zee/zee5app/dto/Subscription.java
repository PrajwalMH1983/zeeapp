package com.zee.zee5app.dto;

import lombok.Data;

@Data
public class Subscription {
	private String subId;
	private String subType;		//Annual , quarterly , monthly
	private String subCountry;
	private String subPaymentMode;
	private String subAutoRenewal;
	private String subExpiry;
	private String subStatus;
	
	private Subscription() {
		System.out.println("Done");
	}
	
	
}
