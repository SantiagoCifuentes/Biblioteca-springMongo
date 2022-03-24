package co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.services;


import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.models.Recurso;
import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.recursoRepository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecursoService
{

    @Autowired
    RecursoRepository recursoRepository;


    public Recurso agregarRecurso(Recurso recurso)
    {
            return  recursoRepository.save(recurso);
    }

    public Optional<Recurso> obtenerRecurso(String id)
    {
        return recursoRepository.findById(id);
    }
}
