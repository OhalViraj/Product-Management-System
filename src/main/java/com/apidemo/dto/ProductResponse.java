package com.apidemo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {

	private List<ProductDto> products;
	
	private long totalElement;
	
	private int totalPages;
	
	private boolean isFirst;
	
	private boolean isLast;
	
	private int pageNo;
	
	private int pageSize;
	
	
}
