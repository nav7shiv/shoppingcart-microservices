package com.navin.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.navin.product.model.Product;
import com.navin.common.OrdersDto;
import com.navin.common.ProductDto;
import com.navin.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository repository;
	
	public List<Product> getAllProducts() {
		OrdersDto orderDto = new OrdersDto();
		orderDto.getStatus();
		
		ProductDto productDto = new ProductDto();
		productDto.getName();
		return repository.findAll();
	}
	
	public Product getProductById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Product saveProduct(Product product) {
		Product savedProduct = repository.save(product);
		savedProduct.getName();
		return repository.save(savedProduct);
	}
	
	public boolean isProductExist(Long id) {
		return repository.existsById(id);
	}
	
	public void deleteProduct(Long id) {
		repository.deleteById(id);
	}

}
