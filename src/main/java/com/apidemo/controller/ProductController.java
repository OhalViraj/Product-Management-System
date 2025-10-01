package com.apidemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apidemo.dto.ProductDto;
import com.apidemo.model.Product;
import com.apidemo.repository.ProductRepository;
import com.apidemo.service.ProductService;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

	@Autowired
	private ProductService productService;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
	@GetMapping("/saveProduct")
	public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto)
	{
		try {

			Boolean saveProduct = productService.saveProduct(productDto);
			if(!saveProduct)
			{
				return new ResponseEntity<>("Product Not Save",HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
			return new ResponseEntity<>("Save Successfully",HttpStatus.CREATED);
	}
	
	
	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts()
	{
		List<ProductDto> allProducts =null;
		try {
			 allProducts = productService.getAllProducts();
			
			if(CollectionUtils.isEmpty(allProducts))
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(allProducts,HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Integer id)
	{
		ProductDto productById =null;
		try {
			productById = productService.getProductById(id);
			
			if(ObjectUtils.isEmpty(productById))
			{
				return new ResponseEntity<>("Product Not Found With Id ="+id,HttpStatus.NOT_FOUND);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(productById,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id)
	{
		Boolean deleteProduct=null;
		try
		{
			deleteProduct = productService.deleteProduct(id);
			
			if(!deleteProduct)
			{
				return new ResponseEntity<>("Product Not Deleted  ",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Product  Deleted ",HttpStatus.OK);
	}
}
