package br.com.teatime.mimao.service;

import br.com.teatime.mimao.entity.Aluno;
import br.com.teatime.mimao.entity.PeriodoTreino;
import br.com.teatime.mimao.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno findById(String id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void delete(String id) {
        alunoRepository.deleteById(id);
    }

    public Aluno saveTreinoAluno(String id, PeriodoTreino historicoTreino) {
        var aluno = findById(id);

        var historicoTreinos = aluno.getHistoricoTreino();

        if (historicoTreinos == null) {
            historicoTreinos = new ArrayList<>();
        }
        historicoTreinos.add(historicoTreino);

        aluno.setHistoricoTreino(historicoTreinos);

        save(aluno);

        return aluno;
    }
}
