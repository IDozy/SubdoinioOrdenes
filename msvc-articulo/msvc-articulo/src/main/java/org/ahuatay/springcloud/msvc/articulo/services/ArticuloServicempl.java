package org.ahuatay.springcloud.msvc.articulo.services;

import org.ahuatay.springcloud.msvc.articulo.entity.Articulo;
import org.ahuatay.springcloud.msvc.articulo.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServicempl implements ArticuloService {

    @Autowired // inyecta la dependencia de una clase que tiene metodos a otra clase
    private ArticuloRepository repository;
    @Override
    @Transactional
    public List<Articulo> listar() {
        return (List<Articulo>) repository.findAll() ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Articulo> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Articulo guardar(Articulo articulo) {
        return repository.save(articulo);
    }

    @Override
    public void eliminar(Long id) {
    repository.deleteById(id);
    }
}
