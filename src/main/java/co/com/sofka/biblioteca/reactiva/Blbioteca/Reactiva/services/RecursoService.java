package co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.services;


import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.models.Recurso;
import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.recursoRepository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RecursoService {

    @Autowired
    public RecursoRepository recursoRepository;


    public Recurso agregarRecurso(Recurso recurso) {
        String  id = UUID.randomUUID().toString().substring(0,10);
        recurso.setId(id);
        return recursoRepository.save(recurso);
    }

    public Optional<Recurso> obtenerRecurso(String id) {
        return recursoRepository.findById(id);
    }
}
