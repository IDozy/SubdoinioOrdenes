package com.huatayordenes.springcloud.msvc.empleados.repositories;

import com.huatayordenes.springcloud.msvc.empleados.models.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado,Long> {
}
