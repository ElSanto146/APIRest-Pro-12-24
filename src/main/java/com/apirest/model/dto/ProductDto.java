package com.apirest.model.dto;

import com.apirest.model.entity.EnumProductCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;


@Builder
public class ProductDto {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotNull(message = "La categoria no puede ser nula")
    private EnumProductCategory category;

    @NotNull(message = "El precio no puede ser nulo")
    @PositiveOrZero(message = "El precio debe ser mayor o igual a 0")
    private Double price;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stock;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, EnumProductCategory category, Double price, Integer stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre no puede estar vacío") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "El nombre no puede estar vacío") String name) {
        this.name = name;
    }

    public @NotNull(message = "La categoria no puede ser nula") EnumProductCategory getCategory() {
        return category;
    }

    public void setCategory(@NotNull(message = "La categoria no puede ser nula") EnumProductCategory category) {
        this.category = category;
    }

    public @NotNull(message = "El precio no puede ser nulo") @PositiveOrZero(message = "El precio debe ser mayor o igual a 0") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "El precio no puede ser nulo") @PositiveOrZero(message = "El precio debe ser mayor o igual a 0") Double price) {
        this.price = price;
    }

    public @NotNull(message = "El stock no puede ser nulo") @Min(value = 0, message = "El stock debe ser mayor o igual a 0") Integer getStock() {
        return stock;
    }

    public void setStock(@NotNull(message = "El stock no puede ser nulo") @Min(value = 0, message = "El stock debe ser mayor o igual a 0") Integer stock) {
        this.stock = stock;
    }
}
