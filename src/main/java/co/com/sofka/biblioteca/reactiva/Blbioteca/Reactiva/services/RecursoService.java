package co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.services;


import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.models.Recurso;
import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.recursoRepository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    public List<Recurso> obtenerTodosRecursos()
    {
        return recursoRepository.findAll();
    }

    public void eliminarRecurso(String id)
    {
         recursoRepository.deleteById(id);
    }


    public Optional<Recurso> actualizarRecurso(String id, Recurso recurso)
    {


        return recursoRepository.findById(id).map(recurso1 ->
        {
            recurso.setId(id);
            return recursoRepository.save(recurso);
        });
    }

    public String prestar(String id)
    {

        String prestamoExitoso = "El prestamo se ha realizado con exito";
//
        Recurso recurso = recursoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso no encontrado"));

        String prestado= "Recurso prestado en la fecha "+ recurso.getFechaPrestamo();
        if(recurso.getDisponible()==false){
                return prestado;
        }

        recurso.setDisponible(false);
        recurso.setFechaPrestamo(LocalDateTime.now());

        Recurso recursoActualizado = recursoRepository.save(recurso);

        return prestamoExitoso + " \n El recurso se prestó en la fecha: " + recursoActualizado.getFechaPrestamo();


    }

    public String devolver(String id)
    {

        String devolucionExitosa = "El recurso se ha devuelto con exito";
//
        Recurso recurso = recursoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso no encontrado"));

        String noPrestado= "El recurso se encuentra disponible";
        if(recurso.getDisponible()==true){
                return noPrestado;
        }

        recurso.setDisponible(true);
        recursoRepository.save(recurso);

        return devolucionExitosa;




    }






}
