package co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.controller;

import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.models.Recurso;
import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.services.RecursoService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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


//    @Test
//    @DisplayName("POST/biblioteca/recurso")
//    void testCreateWidget() throws Exception {
//        // Setup our mocked service
//        Recurso recursotoPost= new Recurso("1","libros","ciencias",true);
//        Recurso recursotoReturn= new Recurso("2","libros","matematicas",true);
//
//        doReturn(recursotoReturn).when(recursoService).agregarRecurso(recursotoPost);
//
//        // Execute the POST request
//        mockMvc.perform(post("/biblioteca/recurso")
//                        .contentType(MediaType.APPLICATION_JSON)
//                         .content(asJsonString(recursotoReturn)))
//
//                // Validate the response code and content type
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//
//                // Validate headers
////                .andExpect(header().string(HttpHeaders.LOCATION, "/rest/widget/1"))
////                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
//
//                // Validate the returned fields
//                .andExpect(jsonPath("$.id", is("2")))
//                .andExpect(jsonPath("$.tipo", is("libros")))
//                .andExpect(jsonPath("$.tematica", is("matematicas")))
//                .andExpect(jsonPath("$.disponible", is(true)));
//    }


//    @Test
//    @DisplayName("PUT/biblioteca/recurso/{id}")
//    void notFoundPutTest() throws Exception {
//        Recurso recurso=new Recurso();
//        String id="1";
//
//        Recurso recursotoUpdate= new Recurso("1","libros","ciencias",true);
//        //Recurso recursotoReturn= new Recurso("1","libros","matematicas", LocalDateTime.now(),true);
//
//        Optional<Recurso> recursotoReturn = Optional.of(new Recurso("1", "libros", "matematicas", true));
//        doReturn(Optional.of(recursotoReturn)).when(recursoService).obtenerRecurso("1");
//        doReturn(recursotoReturn).when(recursoService).actualizarRecurso(id,recursotoUpdate);
//
//        //Execute the PUT request
//
//        mockMvc.perform(put("/biblioteca/recurso/{id}", "1")
//
//
//                        .header("If-Match", "1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(recursotoUpdate)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is("1")))
//                .andExpect(jsonPath("$.tipo", is("libros")))
//                .andExpect(jsonPath("$.tematica", is("matematicas")))
//                .andExpect(jsonPath("$.disponible", is(true)));
//
//
//    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}