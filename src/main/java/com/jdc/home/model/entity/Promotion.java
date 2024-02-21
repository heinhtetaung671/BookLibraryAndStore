package com.jdc.home.model.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = false)
public class Promotion extends ParentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Enumerated(EnumType.STRING)
	private Type type;
	@Column(columnDefinition = "int default 0 check(disPercent < 100)")
	private int disPercent;
	@ColumnDefault("1")
	private boolean active;

	@OneToMany(mappedBy = "promotion")
	private List<BookPromotion> bookPromotion;
	
	public enum Type {
		NONE, NORMAL_DISCOUNT, MONTHLY_DISCOUNT, YEARLY_DISCOUNT, NEWYEAR_DISCOUNT
	}
	
}
