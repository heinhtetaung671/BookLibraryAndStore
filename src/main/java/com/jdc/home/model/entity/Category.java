package com.jdc.home.model.entity;

import org.hibernate.annotations.ColumnDefault;

import com.jdc.home.model.output.CategorySelectionDto;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@SqlResultSetMapping(name = "categoryMapping",
			classes = @ConstructorResult(columns = {@ColumnResult(name = "id"), @ColumnResult(name = "name")}, targetClass = CategorySelectionDto.class)
		)
@NamedNativeQuery(name = "Category.findAll", query = "select c.id id, c.name name from Category c", resultSetMapping = "categoryMapping")
public class Category extends ParentEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String name;
	@ColumnDefault("1")
	private boolean active;
	
}
