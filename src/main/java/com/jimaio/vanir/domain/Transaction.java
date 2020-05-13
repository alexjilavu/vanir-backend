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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="TRANSACTIONS")
@Data
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	protected Long id;
	
	@Column(name="AMOUNT")
	protected Integer amount;
	
	@OneToMany(cascade= {}, fetch=FetchType.EAGER)
	@JoinColumn(name="CURRENCY_ID", nullable=false, updatable=true, insertable=true)
	protected Currency currency;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="SENDER_ACCOUNT_ID", nullable=false)
	protected Account senderAccount;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="RECIPIENT_ACCOUNT_ID", nullable=false)
	protected Account recipientAccount;
	
	@Column(name="DATE")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	
	@Column(name="DESCRIPTION")
	protected String description;
	
	public Transaction() {
		this.id = 0L;
	}
}
