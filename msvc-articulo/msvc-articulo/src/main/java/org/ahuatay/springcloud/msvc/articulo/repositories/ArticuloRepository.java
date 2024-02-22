package org.ahuatay.springcloud.msvc.articulo.repositories;

import org.ahuatay.springcloud.msvc.articulo.entity.Articulo;
import org.springframework.data.repository.CrudRepository;

public interface ArticuloRepository extends CrudRepository<Articulo,Long> {
}
