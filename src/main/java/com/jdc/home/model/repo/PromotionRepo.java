package com.jdc.home.model.repo;

import java.util.List;
import java.util.Optional;

import com.jdc.home.model.CustomRepository;
import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.output.PromotionCheckboxDto;
import com.jdc.home.model.output.PromotionInfoDto;

public interface PromotionRepo extends CustomRepository<Promotion, Integer>{

	List<PromotionCheckboxDto> findPromotionByActive(boolean active);
	
	Optional<Promotion> findPromotionByIdAndActive(Integer id, boolean active);
	
	List<PromotionInfoDto> findPromotionBy();
}
