package com.jdc.home.model.input;

import com.jdc.home.model.entity.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryEditForm {

	private int id;
	@NotBlank(message = "please enter name.")
	private String name;
	private Boolean active;
	
	public Category toCategory() {
		var c = new Category();
		c.setId(id);
		c.setName(name);
		c.setActive(active);
		
		return c;
	}
	
}
