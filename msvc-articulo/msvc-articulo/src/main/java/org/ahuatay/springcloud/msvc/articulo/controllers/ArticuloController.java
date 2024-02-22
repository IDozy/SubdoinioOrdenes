package org.ahuatay.springcloud.msvc.articulo.controllers;


import org.ahuatay.springcloud.msvc.articulo.entity.Articulo;
import org.ahuatay.springcloud.msvc.articulo.services.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/articulo")
@RestController
public class ArticuloController {

    @Autowired
    private ArticuloService service;

    @GetMapping
    public List<Articulo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Articulo> articuloOptional = service.porId(id);
        if (articuloOptional.isPresent()) {
            return ResponseEntity.ok(articuloOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Articulo articulo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(articulo));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> editar(@RequestBody Articulo articulo, @PathVariable Long id) {
        Optional<Articulo> op = service.porId(id);
        if (op.isPresent()) {
            Articulo articuloDB = op.get();

            articuloDB.setName(articulo.getName());
            articuloDB.setPrice(articulo.getPrice());
            articuloDB.setStock(articulo.getStock());


            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(articuloDB));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Articulo> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
