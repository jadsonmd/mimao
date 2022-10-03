package br.com.teatime.mimao.service;

import br.com.teatime.mimao.entity.Aluno;
import br.com.teatime.mimao.entity.Professor;
import br.com.teatime.mimao.repository.AlunoRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    MongoTemplate mongoTemplate;

    public Set<Aluno> findAll(String idProfessor) {
        Professor professor = professorService.findById(idProfessor);
        return professor.getAlunos();
    }

    public Aluno findById(String idProfessor, String idAluno) {
        Professor professor = professorService.findById(idProfessor);

        Set<Aluno> collect = professor.getAlunos().stream().filter(aluno -> aluno.getId().equals(idAluno) ).collect(Collectors.toSet());
        return collect.iterator().next();
    }

    public Aluno create(String idProfessor, Aluno aluno) {
        Professor professor = professorService.findById(idProfessor);
        Set<Aluno> alunos = professor.getAlunos();
        if (alunos == null){
            alunos = new LinkedHashSet<>();
        }

        aluno.setId(new ObjectId().toString());
        alunos.add(aluno);

        professorService.save(professor);

        return findById(idProfessor, aluno.getId());
    }

    public Aluno update(String idProfessor, Aluno aluno) {
        String idAluno = aluno.getId();
        aluno.setId(null);
        String alunoJson = converteAlunoToJson(aluno);

        mongoTemplate.getCollection("professor").updateOne(
                new Document("_id", new ObjectId((idProfessor))).toBsonDocument(),
                Updates.set("alunos.$[aluno]", Document.parse(alunoJson).append("_id", new ObjectId(idAluno))),
                new UpdateOptions().arrayFilters(
                        Arrays.asList(Filters.eq("aluno._id", new ObjectId(idAluno)))
                )
        );

        return findById(idProfessor, idAluno);
    }

    private String converteAlunoToJson(Aluno aluno) {
        String alunoJson = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            alunoJson =  objectMapper.writeValueAsString(aluno);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return alunoJson;
    }

    public void delete(String idProfessor, String idAluno) {
        Aluno aluno = findById(idProfessor, idAluno);
        aluno.setId(null);

        String alunoJson = converteAlunoToJson(aluno);

        System.out.println(alunoJson);

        mongoTemplate.getCollection("professor").updateOne(
                new Document("_id", new ObjectId((idProfessor))).toBsonDocument(),
                Updates.pull("alunos", Document.parse(alunoJson).append("_id", new ObjectId(idAluno)))
        );
    }
}
