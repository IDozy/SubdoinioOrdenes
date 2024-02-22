package org.ahuatay.springcloud.msvc.articulo.services;

import org.ahuatay.springcloud.msvc.articulo.entity.Articulo;

import java.util.List;
import java.util.Optional;

public interface ArticuloService {
    List<Articulo> listar();
    Optional<Articulo> porId(Long id);
    Articulo guardar(Articulo articulo);
    void eliminar(Long id);
}
