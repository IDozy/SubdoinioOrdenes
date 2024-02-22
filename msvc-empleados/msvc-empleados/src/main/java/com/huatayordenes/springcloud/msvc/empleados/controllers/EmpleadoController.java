package com.huatayordenes.springcloud.msvc.empleados.controllers;


import com.huatayordenes.springcloud.msvc.empleados.models.entity.Empleado;
import com.huatayordenes.springcloud.msvc.empleados.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/empleado")
@RestController
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;

    @GetMapping
    public List<Empleado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Empleado> op = service.porId(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Empleado empleado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Empleado empleado, @PathVariable Long id) {
        Optional<Empleado> op = service.porId(id);
        if (op.isPresent()) {
            Empleado empleadoDB = op.get();

            empleadoDB.setName(empleado.getName());
            empleadoDB.setSurname(empleado.getSurname());
            empleadoDB.setDni(empleado.getDni());
            empleadoDB.setAddress(empleado.getAddress());
            empleadoDB.setPhone(empleado.getPhone());
            empleadoDB.setSueldo(empleado.getSueldo());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(empleadoDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Empleado> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
