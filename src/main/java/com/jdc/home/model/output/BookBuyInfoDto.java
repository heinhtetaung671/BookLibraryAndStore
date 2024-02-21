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
public class BookBuyInfoDto {
	private int id;
	private String name;
	private String author;
	private int dtsPrice;
	private int qtyForSale;
	private boolean active;
	private String category;

	private LocalDateTime createAt;
	private LocalDateTime updateAt;

	private String createBy;
	private String updateBy;

	public BookBuyInfoDto(String name, String author, int dtsPrice, int qtyForSale, boolean active, String category) {
		super();
		this.name = name;
		this.author = author;
		this.dtsPrice = dtsPrice;
		this.qtyForSale = qtyForSale;
		this.active = active;
		this.category = category;
	}

	public static void select(CriteriaQuery<BookBuyInfoDto> cq, Root<Book> root) {

		cq.multiselect(root.get(Book_.id), root.get(Book_.name), root.get(Book_.author), root.get(Book_.dtsPrice),
				root.get(Book_.qtyForSale), root.get(Book_.active), root.get(Book_.category).get(Category_.name));

	}

	public static BookBuyInfoDto toDto(Book b) {
		return new BookBuyInfoDto(b.getId(), b.getName(), b.getAuthor(), b.getDtsPrice(), b.getQtyForSale(),
				b.isActive(), b.getCategory().getName(), b.getCreateAt(), b.getUpdateAt(), b.getCreateBy(), b.getUpdateBy());
	}

	public BookBuyInfoDto(int id, String name, String author, int dtsPrice, int qtyForSale, boolean active,
			String category) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.dtsPrice = dtsPrice;
		this.qtyForSale = qtyForSale;
		this.active = active;
		this.category = category;
	}

	public BookBuyInfoDto(int id, String name, String author, int dtsPrice, int qtyForSale, boolean active,
			String category, LocalDateTime createAt, LocalDateTime updateAt, String createBy, String updateBy) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.dtsPrice = dtsPrice;
		this.qtyForSale = qtyForSale;
		this.active = active;
		this.category = category;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.createBy = createBy;
		this.updateBy = updateBy;
	}

}
