package co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.recursoRepository;

import co.com.sofka.biblioteca.reactiva.Blbioteca.Reactiva.models.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


public interface RecursoRepository extends MongoRepository<Recurso,String>
{

}
