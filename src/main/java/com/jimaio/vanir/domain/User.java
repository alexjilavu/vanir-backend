package com.jimaio.vanir.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	public User() {
		this.id = 0L;
	}
	
}
