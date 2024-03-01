package com.huatayordenes.springcloud.msvc.productos.services;

import com.huatayordenes.springcloud.msvc.productos.models.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(Long id);
    Producto guardar(Producto producto);
    void eliminar(Long id);

    Optional<Producto> porNombre(String name);

}
