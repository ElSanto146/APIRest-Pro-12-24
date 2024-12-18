package com.apirest.service;

import com.apirest.model.dto.ProductDto;
import com.apirest.model.entity.EnumProductCategory;
import java.util.List;

public interface IProductService {

    List<ProductDto> allProduct();

    List<ProductDto> allProductByCategory(EnumProductCategory category);

    ProductDto findProductById(Long id);

   ProductDto updateProduct(Long id, ProductDto productDto);

    ProductDto deleteProduct(Long id);

    ProductDto saveProduct1(ProductDto productDto);
}
