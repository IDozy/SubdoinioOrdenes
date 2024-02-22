package com.huatayordenes.springcloud.msvc.productos.services;

import com.huatayordenes.springcloud.msvc.productos.models.entity.Producto;
import com.huatayordenes.springcloud.msvc.productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicempl implements ProductoService{

    @Autowired
    private ProductoRepository repository;

    @Override
    @Transactional
    public List<Producto> listar() {
        return (List<Producto>) repository.findAll() ;
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
