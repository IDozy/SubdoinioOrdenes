package com.huatayordenes.springcloud.msvc.cliente.controllers;

import com.huatayordenes.springcloud.msvc.cliente.models.entity.Cliente;
import com.huatayordenes.springcloud.msvc.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<?> crear(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cliente));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> editar(@RequestBody Cliente cliente, @PathVariable Long id) {
        Optional<Cliente> op = service.porId(id);
        if (op.isPresent()) {
            Cliente clienteDB = op.get();

            clienteDB.setName(cliente.getName());
            clienteDB.setSurname(cliente.getSurname());
            clienteDB.setEmail(cliente.getEmail());
            clienteDB.setPhone(cliente.getPhone());

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
}
