package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zee.zee5app.utils.CustomListSerializer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login")
public class Login implements Comparable<Login>{
	
	@Id
	//@Email
	private String userName;
	@NotBlank
	private String password;
//	@NotBlank
//	private String regId;
	
	
	@OneToOne(fetch = FetchType.LAZY)	//then and then only it will retrieve it or else it will not
   @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})	//Instead of handling the JsonIgnore this is another alternative
	//@JsonSerialize(using = CustomListSerializer.class)
    @JoinColumn(name = "userId")
    @JsonProperty(access=Access.WRITE_ONLY)
	private User user;
	
	@Override
	public int compareTo(Login o) {
		// TODO Auto-generated method stub
		return this.userName.compareTo(o.getUserName());
	}

}
