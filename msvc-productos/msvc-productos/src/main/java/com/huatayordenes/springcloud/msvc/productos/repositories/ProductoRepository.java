package com.huatayordenes.springcloud.msvc.productos.repositories;

import com.huatayordenes.springcloud.msvc.productos.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductoRepository extends CrudRepository<Producto,Long> {
        Optional<Producto> findByName(String name);
}
