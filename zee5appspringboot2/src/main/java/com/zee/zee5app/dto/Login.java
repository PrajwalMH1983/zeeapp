package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
	@Email
	private String userName;
	@NotBlank
	private String password;
//	@NotBlank
//	private String regId;
	
	
	@OneToOne
    //@OneToOne(fetch=FetchType.LAZY)
  //  @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
  //  @JsonSerialize(using = CustomListSerialUser.class)
    @JoinColumn(name = "regId")
    //@JsonProperty(access=Access.WRITE_ONLY)
	private Register register;
	
	@Override
	public int compareTo(Login o) {
		// TODO Auto-generated method stub
		return this.userName.compareTo(o.getUserName());
	}

}
