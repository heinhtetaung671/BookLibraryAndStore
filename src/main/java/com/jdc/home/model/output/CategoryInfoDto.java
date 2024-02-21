package com.jdc.home.model.output;

import java.time.LocalDateTime;

import com.jdc.home.model.entity.Category;
import com.jdc.home.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record CategoryInfoDto(
			int id,
			String name,
			boolean active,
			LocalDateTime createAt,
			LocalDateTime updateAt,
			String createBy,
			String updateBy
		) {

	public static void select(CriteriaQuery<CategoryInfoDto> cq, Root<Category> root) {
			
		cq.multiselect(
					root.get(Category_.id),
					root.get(Category_.name),
					root.get(Category_.active),
					root.get(Category_.createAt),
					root.get(Category_.updateAt),
					root.get(Category_.createBy),
					root.get(Category_.updateBy)
				);
		
	}

}
