package com.jdc.home.model.output;

import com.jdc.home.model.entity.Promotion.Type;

public interface PromotionCheckboxDto {

	int getId();
	String getName();
	Type getType();
	int getDisPercent();
}
