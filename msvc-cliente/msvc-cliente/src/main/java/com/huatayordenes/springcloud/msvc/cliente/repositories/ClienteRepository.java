package com.huatayordenes.springcloud.msvc.cliente.repositories;

import com.huatayordenes.springcloud.msvc.cliente.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {
    Optional<Cliente> findByEmail(String email);
}
