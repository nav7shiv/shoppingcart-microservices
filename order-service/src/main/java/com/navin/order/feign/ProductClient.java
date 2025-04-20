package com.navin.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.navin.common.ProductDto;

@FeignClient(name = "product-service")
public interface ProductClient {
	
	@GetMapping("/products/{id}")
	ResponseEntity<ProductDto> getProductById(@PathVariable Long id);

}
