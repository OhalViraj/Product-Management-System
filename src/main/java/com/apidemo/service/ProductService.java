package com.apidemo.service;


import java.util.List;

import com.apidemo.dto.ProductDto;
import com.apidemo.model.Product;


public interface ProductService {

	public Boolean saveProduct(ProductDto productDto);
	
	public List<ProductDto> getAllProducts();
	
	public ProductDto getProductById(Integer id);
	
	public Boolean deleteProduct(Integer id);
	
}
