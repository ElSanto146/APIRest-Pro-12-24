package com.apirest.repository;

import com.apirest.model.entity.EnumProductCategory;
import com.apirest.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Boolean existsProductByNameAndPriceAndCategory(String name, Double price,
                                                   EnumProductCategory category);

    List<Product> findProductByCategory(EnumProductCategory category);

}
