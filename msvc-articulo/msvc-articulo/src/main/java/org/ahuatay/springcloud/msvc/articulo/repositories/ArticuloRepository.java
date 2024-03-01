package org.ahuatay.springcloud.msvc.articulo.repositories;

import org.ahuatay.springcloud.msvc.articulo.entity.Articulo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticuloRepository extends CrudRepository<Articulo,Long> {
    Optional<Articulo> findByName(String name);
}
