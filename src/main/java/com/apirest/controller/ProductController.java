package com.apirest.controller;

import com.apirest.model.dto.ProductDto;
import com.apirest.model.entity.EnumProductCategory;
import com.apirest.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity<ProductDto> crearProducto(@RequestBody @Valid ProductDto productDto){
        return new ResponseEntity<>(productService.saveProduct1(productDto), HttpStatus.CREATED) ;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> listarProductos(){
        return new ResponseEntity<>(productService.allProduct(), HttpStatus.OK);
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<ProductDto>> listarPorCategoria(@RequestParam EnumProductCategory category){
        return new ResponseEntity<>(productService.allProductByCategory(category), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ProductDto> findByProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct ( @PathVariable Long id,
                                                      @RequestBody @Valid ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
