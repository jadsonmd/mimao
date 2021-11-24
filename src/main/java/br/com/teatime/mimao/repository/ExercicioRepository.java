package br.com.teatime.mimao.repository;

import br.com.teatime.mimao.entity.Exercicio;
import br.com.teatime.mimao.entity.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExercicioRepository extends MongoRepository<Exercicio, String> {
}