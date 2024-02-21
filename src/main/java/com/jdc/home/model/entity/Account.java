package com.jdc.home.model.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Account extends ParentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	
	private String phone;
	
	@ColumnDefault("0")
	private int balance;
	@ColumnDefault("0.0")
	private float point;
	@ColumnDefault("0")
	private boolean locked;
	@ColumnDefault("1")
	private boolean active;
	private String statusMessage;
	
	private String profileImage;
	
	@Enumerated(EnumType.STRING)
	@ColumnDefault(value = "'CUSTOMER'")
	private Authority authority;

	public enum Authority {
		CUSTOMER, MEMBER, ADMIN
	}

}
