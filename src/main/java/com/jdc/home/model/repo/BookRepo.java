package com.jdc.home.model.repo;

import java.util.Optional;

import com.jdc.home.model.CustomRepository;
import com.jdc.home.model.entity.Book;

public interface BookRepo extends CustomRepository<Book, Integer>{

	Optional<Book> findBookByIdAndActive(int id, boolean active);
	
}
