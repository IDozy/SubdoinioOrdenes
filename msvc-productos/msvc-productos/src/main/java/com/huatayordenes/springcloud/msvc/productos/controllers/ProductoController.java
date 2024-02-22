package com.huatayordenes.springcloud.msvc.productos.controllers;

import com.huatayordenes.springcloud.msvc.productos.models.entity.Producto;
import com.huatayordenes.springcloud.msvc.productos.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/producto")
@RestController
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Producto> op = service.porId(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Producto producto, @PathVariable Long id) {
        Optional<Producto> op = service.porId(id);
        if (op.isPresent()) {
            Producto productoDB = op.get();

            productoDB.setName(producto.getName());
            productoDB.setPrice(producto.getPrice());
            productoDB.setDescription(producto.getDescription());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(productoDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Producto> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
