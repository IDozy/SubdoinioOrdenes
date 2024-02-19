package com.huatayordenes.springcloud.msvc.cliente.services;

import com.huatayordenes.springcloud.msvc.cliente.models.entity.Cliente;
import com.huatayordenes.springcloud.msvc.cliente.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicempl implements ClienteService {

    @Autowired // inyecta la dependencia de una clase que tiene metodos a otra clase
    private ClienteRepository repository;
    @Override
    @Transactional
    public List<Cliente> listar() {
        return (List<Cliente>) repository.findAll() ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
    repository.deleteById(id);
    }
}
