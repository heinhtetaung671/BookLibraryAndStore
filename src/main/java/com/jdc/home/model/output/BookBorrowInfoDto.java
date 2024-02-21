package com.jdc.home.model.output;

import java.time.LocalDateTime;

import com.jdc.home.model.entity.Book;
import com.jdc.home.model.entity.Book_;
import com.jdc.home.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBorrowInfoDto{

	private int id;
	private String name;
	private String author;
	private int bpdPrice;
	private int qtyForBorrow;
	private boolean active;
	private String category;

	private LocalDateTime createAt;
	private LocalDateTime updateAt;

	private String createBy;
	private String updateBy;
	
	
	public static void select(CriteriaQuery<BookBorrowInfoDto> cq,Root<Book> root) {
		
		cq.multiselect(
				
				root.get(Book_.id),
				root.get(Book_.name),
				root.get(Book_.author),
				root.get(Book_.bpdPrice),
				root.get(Book_.qtyForBorrow),
				root.get(Book_.active),
				root.get(Book_.category).get(Category_.name)
				
				);
	}
	
	public static BookBorrowInfoDto toDto(Book book) {
		var dto = new BookBorrowInfoDto(book.getId(), book.getName(),
				book.getAuthor(), book.getBpdPrice(), book.getQtyForBorrow(),
				book.isActive(), book.getCategory().getName(),book.getCreateAt(), book.getUpdateAt(), book.getCreateBy(), book.getUpdateBy());
	
		return dto;
	}

	public BookBorrowInfoDto(int id, String name, String author, int bpdPrice, int qtyForBorrow, boolean active,
			String category) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.bpdPrice = bpdPrice;
		this.qtyForBorrow = qtyForBorrow;
		this.active = active;
		this.category = category;
	}

	public BookBorrowInfoDto(int id, String name, String author, int bpdPrice, int qtyForBorrow, boolean active,
			String category, LocalDateTime createAt, LocalDateTime updateAt, String createBy, String updateBy) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.bpdPrice = bpdPrice;
		this.qtyForBorrow = qtyForBorrow;
		this.active = active;
		this.category = category;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.createBy = createBy;
		this.updateBy = updateBy;
	}
	
}
