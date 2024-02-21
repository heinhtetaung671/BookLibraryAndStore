package com.jdc.home.model.output;

import java.time.LocalDateTime;

import com.jdc.home.model.entity.BookBorrow;
import com.jdc.home.model.entity.BookBorrow_;
import com.jdc.home.model.entity.Book_;
import com.jdc.home.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record BorrowBookInfoDto(int id, String bookName, String author, String categoryName, int bpdPrice,
		LocalDateTime borrowDate, LocalDateTime returnDate) {

	public static void select(CriteriaQuery<BorrowBookInfoDto> cq, Root<BookBorrow> root) {
		cq.multiselect(
				root.get(BookBorrow_.id),
				root.get(BookBorrow_.book).get(Book_.name),
				root.get(BookBorrow_.book).get(Book_.author),
				root.get(BookBorrow_.book).get(Book_.category).get(Category_.name),
				root.get(BookBorrow_.book).get(Book_.bpdPrice),
				root.get(BookBorrow_.borrowDate),
				root.get(BookBorrow_.returnDate)
				);
	}

}
