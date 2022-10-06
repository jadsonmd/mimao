package br.com.teatime.mimao.service;

import br.com.teatime.mimao.entity.Aluno;
import br.com.teatime.mimao.entity.Ficha;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FichaService {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Ficha> findAll(String idProfessor, String idAluno) {
        return alunoService.findById(idProfessor, idAluno).getFichas();
    }

    public Ficha findById(String idProfessor, String idAluno, String idFicha) {
        Aluno aluno = alunoService.findById(idProfessor, idAluno);

        return aluno.getFichas().stream().filter(ficha -> ficha.getId().equals(idFicha)).collect(Collectors.toList()).get(0);
    }

    public Ficha create(String idProfessor, String idAluno, Ficha ficha) {
        LocalDateTime dataInicioTreino = ficha.getDataInicioTreino();
        ficha.setDataInicioTreino(null);
        String fichaJson = converteFichaToJson(ficha);
        ObjectId idFicha = new ObjectId();

        mongoTemplate.getCollection("professor").updateOne(
                new Document("_id", new ObjectId((idProfessor))).toBsonDocument(),
                Updates.push("alunos.$[aluno].fichas", Document.parse(fichaJson).append("_id", idFicha).append("dataInicioTreino", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dataInicioTreino))),
                new UpdateOptions().arrayFilters(
                        Arrays.asList(Filters.eq("aluno._id", new ObjectId(idAluno)))
                )
        );
        Ficha byId = findById(idProfessor, idAluno, idFicha.toString());
        return byId;
    }

    public Ficha update(String idProfessor, String idAluno, Ficha ficha) {
        String idFicha = ficha.getId();
        LocalDateTime dataInicioTreino = ficha.getDataInicioTreino();
        ficha.setDataInicioTreino(null);

        ficha.setId(null);

        String fichaJson = converteFichaToJson(ficha);

        mongoTemplate.getCollection("professor").updateOne(
                new Document("_id", new ObjectId((idProfessor))).toBsonDocument(),
                Updates.set("alunos.$[aluno].fichas.$[ficha]", Document.parse(fichaJson).append("_id", new ObjectId(idFicha)).append("dataInicioTreino", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dataInicioTreino))),
                new UpdateOptions().arrayFilters(
                        Arrays.asList(
                                Filters.eq("aluno._id", new ObjectId(idAluno)),
                                Filters.eq("ficha._id", new ObjectId(idFicha))
                        )
                )
        );
        return findById(idProfessor, idAluno, idFicha);
    }

    private String converteFichaToJson(Ficha ficha) {
        String fichaJson = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            fichaJson =  objectMapper.writeValueAsString(ficha);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return fichaJson;
    }

}
