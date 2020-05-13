package com.jimaio.vanir.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="CARDS")
@Data
public class Card implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	protected Long id;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="ACCOUNT_ID", nullable=false, updatable=false, insertable=true)
	protected Account account;
	
	@Column(name="CARD_NUMBER")
	protected String cardNumber;
	
	@Column(name="OWNER_NAME")
	protected String ownerName;
	
	@Column(name="EXP_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date expirationDate;
	
	@Column(name="CREATION_DATE", insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	
	@Column(name="CCV")
	protected String ccv;
	
	@Column(name="PIN_CODE")
	protected String pinCode;
	
	public Card() {
		this.id = 0L;
	}
}
