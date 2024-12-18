package com.apirest.mapper;

import com.apirest.model.dto.ProductDto;
import com.apirest.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    //Metodo para mapear ProductDto a Product
    Product toProduct(ProductDto productDto);

    //Metodo para mapear Product a ProductDto
    ProductDto toProductDto(Product product);

    //Metodo para mapear una lista de Product a una lista de ProductDto
    List<ProductDto> toProductDtoList(List<Product> products);

    //Metodo para actualizar un Product con los datos de un ProductDto
    Product updateProduct(@MappingTarget Product product, ProductDto productDto);

}
