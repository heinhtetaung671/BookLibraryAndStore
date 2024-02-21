package com.jdc.home.model.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookPromotion extends ParentEntity{

	@EmbeddedId
	private BookPromotionPk bookPromotionPk;
	
	@ManyToOne
	@MapsId("bookId")
	private Book book;
	@ManyToOne
	@MapsId("promotionId")
	private Promotion promotion;

	@ColumnDefault("1")	
	private boolean active;
}
