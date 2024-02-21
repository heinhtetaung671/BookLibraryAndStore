package com.jdc.home.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoForPagination {

	private int page;
	private int pageSize = 10;
	
}
