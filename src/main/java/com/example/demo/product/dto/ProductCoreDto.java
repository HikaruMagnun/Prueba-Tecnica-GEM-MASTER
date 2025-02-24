package com.example.demo.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCoreDto {
    @NotNull
    private String nombre;
    private Double precio;
    private Integer stock;
    private Long categoriaId;
}