package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subscription")
public class Subscription implements Comparable<Subscription>{
	
	@Id
	private String subId;
	@NotNull
	private String dateOfPayment;
	@NotNull
	private String subExpiry;
	@NotNull
	private int subAmount;
	@NotBlank
	private String subPaymentMode;
	@NotBlank
	private String subStatus;
	@NotBlank
	private String subType;		//Annual , quarterly , monthly
	@NotBlank
	private String subAutoRenewal;

	@OneToOne
	@JoinColumn(name = "regId")
	@JsonProperty(access = Access.WRITE_ONLY)
	private User register;
	
	@Override
	public int compareTo(Subscription o) {
		// TODO Auto-generated method stub
		return this.subId.compareTo(o.getSubId());
	}
	
}
