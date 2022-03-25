package co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.controller;

import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.models.Recurso;
import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.services.RecursoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest
@AutoConfigureMockMvc
class RecursoControllerTest
{

    @MockBean
    private RecursoService recursoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void obtenerRecursos() throws Exception
    {
        List<Recurso>recursos = new ArrayList<Recurso>();

        doReturn(recursos).when(recursoService).obtenerTodosRecursos();

        mockMvc.perform(get("/biblioteca/recursos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    @DisplayName("GET/biblioteca/recurso/{id}")
    void Recursocreado() throws Exception {


        Recurso recursotoReturn= new Recurso("1","libros","ciencias", LocalDateTime.now(),true);
        doReturn(Optional.of(recursotoReturn)).when(recursoService).obtenerRecurso("1");

        mockMvc.perform(get("/biblioteca/recurso/{id}","1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate headers
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.tipo", is("libros")))
                .andExpect(jsonPath("$.tematica", is("ciencias")))
                .andExpect(jsonPath("$.disponible", is(true)));


    }
}