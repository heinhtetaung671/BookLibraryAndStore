package com.jdc.home.model.input;

import com.jdc.home.model.entity.Book;
import com.jdc.home.model.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEditForm {

	private int id;
	@NotBlank(message = "please enter name.")
	private String name;
	@NotBlank(message = "please enter author name.")
	private String author;
	private int wsPrice;
	private int dtsPrice;
	private int bpdPrice;
	private int qtyForBorrow;
	private int qtyForSale;
	private boolean active;
	@NotNull(message = "please select one category.")
	private Category category;

	public BookEditForm() {
		// TODO Auto-generated constructor stub
	}

	public Book toBook() {
		var book = new Book();
		book.setId(id);
		book.setName(name);
		book.setAuthor(author);
		book.setWsPrice(wsPrice);
		book.setDtsPrice(dtsPrice);
		book.setBpdPrice(bpdPrice);
		book.setQtyForBorrow(qtyForBorrow);
		book.setQtyForSale(qtyForSale);
		book.setActive(active);
		book.setCategory(category);
		return book;

	}

	public static BookEditForm toDto(Book b) {

		var dto = new BookEditForm();
		dto.setId(b.getId());
		dto.setName(b.getName());
		dto.setAuthor(b.getAuthor());
		dto.setWsPrice(b.getWsPrice());
		dto.setDtsPrice(b.getDtsPrice());
		dto.setBpdPrice(b.getBpdPrice());
		dto.setQtyForBorrow(b.getQtyForBorrow());
		dto.setQtyForSale(b.getQtyForSale());
		dto.setActive(b.isActive());
		dto.setCategory(b.getCategory());

		return dto;

	}

	public BookEditForm(int id, @NotBlank(message = "please enter name.") String name,
			@NotBlank(message = "please enter author name.") String author, int wsPrice, int dtsPrice, int bpdPrice,
			int qtyForBorrow, int qtyForSale, boolean active,
			@NotNull(message = "please select one category.") Category category) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.wsPrice = wsPrice;
		this.dtsPrice = dtsPrice;
		this.bpdPrice = bpdPrice;
		this.qtyForBorrow = qtyForBorrow;
		this.qtyForSale = qtyForSale;
		this.active = active;
		this.category = category;
	}

}
