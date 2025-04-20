package com.navin.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.navin.common.ProductDto;
import com.navin.product.mapper.ProductMapper;
import com.navin.product.model.Product;
import com.navin.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService service;
	
	@Autowired
	private ProductMapper productMapper;
	
	@GetMapping
	public List<ProductDto> getAllProducts() {
		List<Product> allProducts = service.getAllProducts();
		return productMapper.toProductDtos(allProducts);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
		Product product = service.getProductById(id);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productMapper.toProductDto(product));
	}
	
	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		Product savedProduct = service.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Product created successfully with id: " + savedProduct.getId());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
		if (!service.isProductExist(id)) {
			return ResponseEntity.notFound().build();
		}
		updatedProduct.setId(id);
		Product savedProduct = service.saveProduct(updatedProduct);
		return ResponseEntity.ok(savedProduct);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		if (!service.isProductExist(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Product id not found.");
		}
		service.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Product deleted successfully.");
	}

}
