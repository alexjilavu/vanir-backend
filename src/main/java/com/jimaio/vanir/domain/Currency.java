package com.jimaio.vanir.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CURRENCIES")
@Data
public class Currency implements Serializable {
	
	private static final long serialVersionUID = -7421855307575384726L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	protected Long id;
	
	@Column(name="NAME")
	protected String name;
	
	public Currency() {
		this.id = 0L;
	}
}
