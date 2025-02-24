package com.example.demo.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.category.dto.CategoryCoreDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    public Categoria createCategoria(CategoryCoreDto categoria) {
        return categoriaRepository.save(Categoria.builder()
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build());
    }

    public Categoria updateCategoria(Long id, CategoryCoreDto categoriaUpdated) {

        return categoriaRepository.findById(id)
                .map(cate -> {
                    cate.setNombre(categoriaUpdated.getNombre());
                    cate.setDescripcion(categoriaUpdated.getDescripcion());
                    return categoriaRepository.save(cate);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    public void deleteCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
