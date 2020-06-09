//package com.jimaio.vanir.domain;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//
//import lombok.Data;
//
//@Entity
//@Table(name = "ROLE")
//@Data
//public class Role implements GrantedAuthority {
//	
//	private static final long serialVersionUID = 6003769338591378192L;
//
//	@Id
//	@Column(name = "ID")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	public Role() {
//		
//	}
//	
//	public Role(String authority) {
//		this.authority = authority;
//	}
//	
//	@Column(name = "AUTHORITY")
//	private String authority;
//
//	public String getAuthority() {
//		return authority;
//	}
//
//}
