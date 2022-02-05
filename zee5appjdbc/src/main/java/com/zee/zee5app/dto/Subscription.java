package com.zee.zee5app.dto;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class Subscription implements Comparable<Subscription>{
	
	@Setter(value = AccessLevel.NONE)
	private String subId;
	private String subType;		//Annual , quarterly , monthly
	private String subCountry;
	private String subPaymentMode;
	private String subAutoRenewal;
	private String dateOfPayment;
	private String subExpiry;
	private String subStatus;
	private int subAmount;
	private String regId;
	
	public Subscription() {
		
	}
	

	public Subscription(String subId, String subType, String subCountry, String subPaymentMode, String subAutoRenewal,
			String dateOfPayment, String subExpiry, String subStatus, int subAmount , String regId) {
		super();
		this.subId = subId;
		this.subType = subType;
		this.subCountry = subCountry;
		this.subPaymentMode = subPaymentMode;
		this.subAutoRenewal = subAutoRenewal;
		this.dateOfPayment = dateOfPayment;
		this.subExpiry = subExpiry;
		this.subStatus = subStatus;
		this.subAmount = subAmount;
		this.regId = regId;
	}
	
	public void setId(String subId) throws InvalidIdLengthException {
		if(subId.length()<6)
			throw new InvalidIdLengthException("Id length is less than 6");
		this.subId = subId;
	}
	
	@Override
	public int compareTo(Subscription o) {
		// TODO Auto-generated method stub
		return this.subId.compareTo(o.getSubId());
	}
	
}
