package com.jimaio.vanir.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="USERS")
@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	protected Long id;
	
	@Column(name="NAME")
	protected String name;
	
	@Column(name = "EMAIL")
	protected String email;
	
	@Column(name = "PASSWORD")
	protected String password;
	
	@Column(name="PHONE_NUMBER")
	protected String phoneNumber;
	
	@Column(name="BIRTH_DATE")
	protected Date birthDate;
	
	@Column(name="ADDRESS")
	protected String address;
	
	@Column(name="API_KEY")
	protected String apiKey;
	
	@Column(name="AVATAR_URL", length = 700)
	protected String avatarUrl;
	
	@JsonIgnore
	@OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER, mappedBy="user", targetEntity=Account.class)
	public Account account;
	
	public User() {
		this.id = 0L;
	}
	
}
