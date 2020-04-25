package com.jimaio.vanir.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name="FIREBASE_ID")
	protected String firebaseId;
	
	@Column(name="NAME")
	protected String name;
	
	@Column(name="PHONE_NUMBER")
	protected String phoneNumber;
	
	@Column(name="BIRTH_DATE")
	protected Date birthDate;
	
	@Column(name="ADDRESS")
	protected String address;
	
	public User() {
		
	}
}
