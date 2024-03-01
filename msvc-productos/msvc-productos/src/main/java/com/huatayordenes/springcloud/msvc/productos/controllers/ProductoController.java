package com.huatayordenes.springcloud.msvc.productos.controllers;

import com.huatayordenes.springcloud.msvc.productos.models.entity.Producto;
import com.huatayordenes.springcloud.msvc.productos.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<?> crear(@Valid @RequestBody Producto producto, BindingResult result) {
        if (service.porNombre(producto.getName()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Ups!!", "Ese producto ya existe"));
        }
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        Optional<Producto> op = service.porId(id);
        if (op.isPresent()) {
            Producto productoDB = op.get();
            if(!producto.getName().equalsIgnoreCase(productoDB.getName()) && service.porNombre(producto.getName()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Ups !!!", "Ya Existe ese producto"));
            }
            productoDB.setName(producto.getName());
            productoDB.setPrice(producto.getPrice());
            productoDB.setDescription(producto.getDescription());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(productoDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Producto> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    private ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Error !!!" + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
