package com.huatayordenes.springcloud.msvc.empleados.services;



import com.huatayordenes.springcloud.msvc.empleados.models.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> listar();
    Optional<Empleado> porId(Long id);
    Empleado guardar(Empleado empleado);
    void eliminar(Long id);

    Optional<Empleado> porDni(String dni);

}
