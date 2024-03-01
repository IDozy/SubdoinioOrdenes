package com.huatayordenes.springcloud.msvc.cliente.controllers;

import com.huatayordenes.springcloud.msvc.cliente.models.entity.Cliente;
import com.huatayordenes.springcloud.msvc.cliente.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequestMapping("/api/cliente")
@RestController
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = service.porId(id);
        if (clienteOptional.isPresent()) {
            return ResponseEntity.ok(clienteOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if(service.porEmail(cliente.getEmail()).isPresent()){
            return  ResponseEntity.badRequest().body(Collections.singletonMap("Ups!!! ","ese correo ya esta siendo usado :c"));
        }
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cliente));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        Optional<Cliente> op = service.porId(id);
        if (op.isPresent()) {
            Cliente clienteDB = op.get();
            if(!cliente.getEmail().equalsIgnoreCase(clienteDB.getEmail()) && service.porEmail(cliente.getEmail()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Ups!!! ", "Ya Existe el email en otro Cliente"));
            }
            clienteDB.setName(cliente.getName());
            clienteDB.setSurname(cliente.getSurname());
            clienteDB.setEmail(cliente.getEmail());
            clienteDB.setPhone(cliente.getPhone());
            clienteDB.setAddress(cliente.getAddress());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(clienteDB));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Cliente> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Error!!! " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
