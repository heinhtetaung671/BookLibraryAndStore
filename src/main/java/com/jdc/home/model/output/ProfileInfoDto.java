package com.jdc.home.model.output;

import java.time.LocalDateTime;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Account_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record ProfileInfoDto (
			int id,
			String name,
			String phone,
			String email,
			Integer balance,
			Float point,
			Boolean active,
			Boolean locked,
			String profileImage,
			String statusMessage,
			LocalDateTime createAt
		){

	public static void select(CriteriaQuery<ProfileInfoDto> cq, Root<Account> root) {
		cq.multiselect(
				root.get(Account_.id),
				root.get(Account_.name),
				root.get(Account_.phone),
				root.get(Account_.email),
				root.get(Account_.balance),
				root.get(Account_.point),
				root.get(Account_.active),
				root.get(Account_.locked),
				root.get(Account_.profileImage),
				root.get(Account_.statusMessage),
				root.get(Account_.createAt)
				);
	}
	
}
