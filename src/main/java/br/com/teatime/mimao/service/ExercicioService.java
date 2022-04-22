package br.com.teatime.mimao.service;

import br.com.teatime.mimao.entity.Exercicio;
import br.com.teatime.mimao.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<Exercicio> findAll() {
        return exercicioRepository.findAll();
    }

    public Exercicio findById(String id) {
        return exercicioRepository.findById(id).orElse(null);
    }

    public Exercicio save(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public void delete(String id) {
        exercicioRepository.deleteById(id);
    }
}
