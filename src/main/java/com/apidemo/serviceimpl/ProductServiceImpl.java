package com.apidemo.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.apidemo.dto.ProductDto;
import com.apidemo.model.Product;
import com.apidemo.repository.ProductRepository;
import com.apidemo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Boolean saveProduct(ProductDto productDto) {
		// TODO Auto-generated method stub
		/*
		 * Product product=new Product(); product.setId(productDto.getId());
		 * product.setName(productDto.getName());
		 * product.setDescription(product.getDescription());
		 * product.setPrice(productDto.getPrice());
		 * product.setQuantity(productDto.getQuantity());
		 */		
		Product product = mapper.map(productDto, Product.class);
		Product save = productRepository.save(product);
		
		if(ObjectUtils.isEmpty(save))
		{
			return false;
		}
		
		return true;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> productsList = productRepository.findAll();
		
		List<ProductDto> productDtoList=productsList.stream().map(product->mapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtoList;
	}

	@Override
	public ProductDto getProductById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Product> findByProduct = productRepository.findById(id);
		if(findByProduct.isPresent())
		{
			Product product = findByProduct.get();
			ProductDto productDto = mapper.map(product, ProductDto.class);
			
			return productDto;
		}
		
		return null;
	}

	@Override
	public Boolean deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		Optional<Product> findByProduct = productRepository.findById(id);
		if(findByProduct.isPresent())
		{
			Product product = findByProduct.get();
			productRepository.delete(product);
			return true;
		}
		return false;
	}
	
}
