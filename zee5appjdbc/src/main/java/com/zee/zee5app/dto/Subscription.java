package com.zee.zee5app.dto;

import lombok.Data;

@Data
public class Subscription implements Comparable<Subscription>{
	private String subId;
	private String subType;		//Annual , quarterly , monthly
	private String subCountry;
	private String subPaymentMode;
	private String subAutoRenewal;
	private String dateOfPurchase;
	private String subExpiry;
	private String subStatus;
	private int subAmount;
	
	public Subscription() {
		
	}
	

	public Subscription(String subId, String subType, String subCountry, String subPaymentMode, String subAutoRenewal,
			String dateOfPurchase, String subExpiry, String subStatus, int subAmount) {
		super();
		this.subId = subId;
		this.subType = subType;
		this.subCountry = subCountry;
		this.subPaymentMode = subPaymentMode;
		this.subAutoRenewal = subAutoRenewal;
		this.dateOfPurchase = dateOfPurchase;
		this.subExpiry = subExpiry;
		this.subStatus = subStatus;
		this.subAmount = subAmount;
	}
	
	@Override
	public int compareTo(Subscription o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
