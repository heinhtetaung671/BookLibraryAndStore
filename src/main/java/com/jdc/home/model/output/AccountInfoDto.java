package com.jdc.home.model.output;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Account.Authority;
import com.jdc.home.model.entity.Account_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record AccountInfoDto(
			int id,
			String name,
			String email,
			Authority authority,
			String phone,
			Integer balance,
			Float point,
			Boolean active,
			Boolean locked,
			String statusMessage
		) {

	public static void select(CriteriaQuery<AccountInfoDto> cq, Root<Account> root) {
		
		cq.multiselect(
				root.get(Account_.id),
				root.get(Account_.name),
				root.get(Account_.email),
				root.get(Account_.authority),
				root.get(Account_.phone),
				root.get(Account_.balance),
				root.get(Account_.point),
				root.get(Account_.active),
				root.get(Account_.locked),
				root.get(Account_.statusMessage)
				);
		
	}

}
