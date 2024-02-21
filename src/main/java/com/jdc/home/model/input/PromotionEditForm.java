package com.jdc.home.model.input;

import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.entity.Promotion.Type;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionEditForm {

	private int id;
	@NotBlank(message = "please enter name.")
	private String name;
	@NotNull(message = "please select type")
	private Type type;
	private boolean active;
	@Min(value = 1, message = "please input at lease 1 percent.")
	private int disPercent;
	
	public static PromotionEditForm from(Promotion form) {
		var dto = new PromotionEditForm();
		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setType(form.getType());
		dto.setDisPercent(form.getDisPercent());
		
		return dto;
	}

		
}
