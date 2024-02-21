package com.jdc.home.model.entity;

import java.time.LocalDateTime;

import com.jdc.home.model.entity.BookBorrow.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookBorrowFoc {

	@Id
	private String id;
	@ManyToOne
	private Book book;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	
}
