package com.example.demo.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.category.Categoria;
import com.example.demo.category.CategoryRepository;
import com.example.demo.product.dto.ProductCoreDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Producto> getAllProductos() {
        return productRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Producto createProducto(ProductCoreDto productDto) {
        Categoria categoria = categoryRepository.findById(productDto.getCategoriaId())
                .orElseThrow(
                        () -> new RuntimeException("Categoría no encontrada con ID: " + productDto.getCategoriaId()));

        return productRepository.save(Producto.builder()
                .nombre(productDto.getNombre())
                .precio(productDto.getPrecio())
                .stock(productDto.getStock())
                .categoria(categoria)
                .build());
    }

    public Producto updateProducto(Long id, ProductCoreDto productDto) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    Categoria categoria = categoryRepository.findById(productDto.getCategoriaId())
                            .orElseThrow(() -> new RuntimeException(
                                    "Categoría no encontrada con ID: " + productDto.getCategoriaId()));

                    existingProduct.setNombre(productDto.getNombre());
                    existingProduct.setPrecio(productDto.getPrecio());
                    existingProduct.setStock(productDto.getStock());
                    existingProduct.setCategoria(categoria);

                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public void deleteProducto(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productRepository.deleteById(id);
    }
}