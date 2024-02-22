package com.huatayordenes.springcloud.msvc.empleados.services;

import com.huatayordenes.springcloud.msvc.empleados.models.entity.Empleado;
import com.huatayordenes.springcloud.msvc.empleados.repositories.EmpleadoRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicempl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository repository;

    @Override
    @Transactional
    public List<Empleado> listar() {
        return (List<Empleado>) repository.findAll() ;
    }

    @Override
    public Optional<Empleado> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return repository.save(empleado);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
