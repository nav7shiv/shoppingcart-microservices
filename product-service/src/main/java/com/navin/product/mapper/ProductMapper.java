package com.navin.product.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.navin.common.ProductDto;
import com.navin.product.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	Product toProduct(ProductDto productDto);
	ProductDto toProductDto(Product product);
	
	List<Product> toProducts(List<ProductDto> productDtos);
	List<ProductDto> toProductDtos(List<Product> products);
	
}
