package com.example.demo.product;

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

import com.example.demo.product.dto.ProductCoreDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productService.getAllProductos();
        return ResponseEntity.ok()
                .body(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productService.getProductoById(id);
        return ResponseEntity.ok()
                .body(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody ProductCoreDto productDto) {
        Producto nuevoProducto = productService.createProducto(productDto);
        return ResponseEntity.status(201)
                .body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody ProductCoreDto productDto) {
        Producto productoActualizado = productService.updateProducto(id, productDto);
        return ResponseEntity.ok()
                .body(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productService.deleteProducto(id);
        return ResponseEntity.noContent()
                .build();
    }
}
