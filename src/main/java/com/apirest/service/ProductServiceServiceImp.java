package com.apirest.service;

import com.apirest.exception.AppExceptions;
import com.apirest.mapper.ProductMapper;
import com.apirest.model.dto.ProductDto;
import com.apirest.model.entity.EnumProductCategory;
import com.apirest.model.entity.Product;
import com.apirest.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceServiceImp implements IProductService {
   //Inyección de dependencias por constructor (Buena práctica)
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceServiceImp(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> allProduct() {
        List<ProductDto> productDtoList = productMapper.toProductDtoList(productRepository.findAll());
        if (productDtoList.isEmpty()){
            throw new AppExceptions("No hay productos para mostrar", HttpStatus.NO_CONTENT);
        }
        return productDtoList;
    }

    @Override
    public List<ProductDto> allProductByCategory(EnumProductCategory category) {
        List<ProductDto> productDtoList = productMapper.toProductDtoList
                (productRepository.findProductByCategory(category));
        if (productDtoList.isEmpty()){
            throw new AppExceptions("No hay productos en esta categoria", HttpStatus.NO_CONTENT);
        }
        return productDtoList;
    }

    @Override
    public ProductDto findProductById(Long id) {
        Product product= productRepository.findById(id).orElseThrow(()->
                new AppExceptions("El producto con id:[" +id+ "] no existe",
                        HttpStatus.NOT_FOUND));
        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
       Product product = productMapper.toProduct( findProductById(id));
       product.setName(productDto.getName());
       product.setPrice(productDto.getPrice());
       product.setCategory(productDto.getCategory());
       product.setStock(productDto.getStock());

       existProduct(product.getName(), product.getPrice(), product.getCategory());

        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto saveProduct1(ProductDto productDto) {
        existProduct(productDto.getName(), productDto.getPrice(), productDto.getCategory());

        Product product = productMapper.toProduct(productDto);
        productRepository.save(product);
        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDto deleteProduct(Long id) {
        ProductDto productDto = findProductById(id);
       productRepository.deleteById(id);
       return productDto;
    }

    private void existProduct (String name, Double price, EnumProductCategory category){
        if (productRepository.existsProductByNameAndPriceAndCategory(name, price, category)){
            throw new AppExceptions("El producto "+ name +" ya existe",
                    HttpStatus.CONFLICT);
        }
    }
}

/*
    public ProductDto updateProduct1(Long id, ProductDto productDto) {
        Product product = productMapper.toProduct( findProductById(id));
        productMapper.updateProduct(product, productDto);
        existProduct(product.getName(), product.getPrice(), product.getCategory());
        return productMapper.toProductDto(productRepository.save(product));
    }
*/
