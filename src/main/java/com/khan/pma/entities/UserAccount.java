
package com.khan.pma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "user_accounts")
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="user_accounts_seq")
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "username")
	private String username;
	
	private String email;
	
	private String password;
	
	
	private String role;

	private boolean enabled = true;
	
	public UserAccount() {
		
	}


	
}