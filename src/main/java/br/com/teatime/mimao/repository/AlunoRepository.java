package br.com.teatime.mimao.repository;

import br.com.teatime.mimao.entity.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
}