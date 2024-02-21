package com.jdc.home.model.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Account.Authority;
import com.jdc.home.model.entity.Account_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSearchForm {

	private String name;
	private String email;
	private String phone;
	private Authority authority;
	private Integer balance;
	private Float point;
	private Boolean active;
	private Boolean locked;
	
	
	private int page;
	private int pageSize = 10;
	private String sortBy;
	
	public Predicate[] where(CriteriaBuilder cb, Root<Account> root) {
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name))
			predicates.add(cb.like(cb.lower(root.get(Account_.name)), name.toLowerCase().concat("%")));
		
		if(StringUtils.hasLength(email))
			predicates.add(cb.equal(root.get(Account_.email), email));

		if(StringUtils.hasLength(phone))
			predicates.add(cb.like(cb.lower(root.get(Account_.phone)), phone.toLowerCase().concat("%")));

		if(authority != null)
			predicates.add(cb.equal(root.get(Account_.authority), authority));

		if(balance != null)
			predicates.add(cb.equal(root.get(Account_.balance), balance));

		if(point != null)
			predicates.add(cb.equal(root.get(Account_.point), point));

		if(active != null)
			predicates.add(cb.equal(root.get(Account_.active), active));

		if(locked != null)
			predicates.add(cb.equal(root.get(Account_.locked), locked));

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
}
