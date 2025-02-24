package com.example.demo.category;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.category.dto.CategoryCoreDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoryService.getAllCategorias();
        return ResponseEntity.ok()
                .body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Categoria categoria = categoryService.getCategoriaById(id);
        return ResponseEntity.ok()
                .body(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody CategoryCoreDto categoriaDto) {
        Categoria nuevaCategoria = categoryService.createCategoria(categoriaDto);
        return ResponseEntity.status(201)
                .body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody CategoryCoreDto categoriaDto) {
        Categoria categoriaActualizada = categoryService.updateCategoria(id, categoriaDto);
        return ResponseEntity.ok()
                .body(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoryService.deleteCategoria(id);
        return ResponseEntity.noContent()
                .build();
    }
}
