package com.jdc.home.model.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book extends ParentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String author;

	private int wsPrice;
	private int dtsPrice;
	private int bpdPrice;
	private int qtyForBorrow;
	private int qtyForSale;
	@ColumnDefault("1")
	private boolean active;
	@ManyToOne(optional = false)
	private Category category;
	@OneToMany(mappedBy = "book")
	private List<BookPromotion> bookPromotions; 
	
}
