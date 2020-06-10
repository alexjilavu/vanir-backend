package com.jimaio.vanir.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
@Data
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

	private static final long serialVersionUID = 569649378652981629L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	protected Long id;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="USER_ID", nullable=false, updatable=true, insertable=true)
	protected User user;
	
	@Column(name="BALANCE")
	protected Integer balance;
	
	@JoinColumn(name="CURRENCY_ID", nullable=false, updatable=true, insertable=true)
	protected Currency currency;
	
	@Column(name="CREATION_DATE", insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="account", targetEntity=Card.class, orphanRemoval=true)
	protected List<Card> cards;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="recipientAccount", targetEntity=Transaction.class)
	protected List<Transaction> receivedTransactions;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="senderAccount", targetEntity=Transaction.class)
	protected List<Transaction> sentTransactions;
	
	public Account() {
		this.id = 0L;
	}
}
