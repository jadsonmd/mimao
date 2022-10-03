package br.com.teatime.mimao.repository;

import br.com.teatime.mimao.entity.Aluno;
import br.com.teatime.mimao.entity.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorRepository extends MongoRepository<Professor, String> {

}