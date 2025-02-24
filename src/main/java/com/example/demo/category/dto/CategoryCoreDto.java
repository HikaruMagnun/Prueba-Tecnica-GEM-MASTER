package com.example.demo.category.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CategoryCoreDto {
    @NotNull
    private String nombre;

    private String descripcion;

}
