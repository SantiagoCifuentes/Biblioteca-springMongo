package co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.controller;


import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.models.Recurso;
import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.services.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/biblioteca")
public class RecursoController
{

    @Autowired
    RecursoService recursoService;

    @PostMapping("/recurso")
    @ResponseStatus(HttpStatus.CREATED)
    public Recurso save(@RequestBody Recurso recurso){
        return recursoService.agregarRecurso(recurso);
    }

    @GetMapping("/recurso/{id}")
    public Optional<Recurso> obtenerRecurso(@PathVariable("id")String id)
    {
        return recursoService.obtenerRecurso(id);
    }

    @GetMapping(value= "/recursos")
    public List<Recurso>obtenerRecursos()
    {
        return recursoService.obtenerTodosRecursos();
    }

    @DeleteMapping("/recursos/{id}")
    public void deleteRecurso(@PathVariable("id")String id)
    {
              recursoService.eliminarRecurso(id);
    }

    @PutMapping("/recurso/{id}")
    public Optional<Recurso> actualizarRecurso(@PathVariable ("id")String id,@RequestBody Recurso recurso)
    {
        return recursoService.actualizarRecurso(id,recurso);
    }

    @PutMapping("/prestar/{id}")
    public String prestar(@PathVariable ("id")String id)
    {
        return recursoService.prestar(id);
    }
    @PutMapping("/devolver/{id}")
    public String devolver(@PathVariable ("id")String id)
    {
        return recursoService.prestar(id);
    }

    @GetMapping("/recursos/recomendacion")
    public List<Recurso>recomendar(@RequestParam String tipo, @RequestParam String tematica)
    {
        return  recursoService.recomendar(tipo,tematica);
    }








}
