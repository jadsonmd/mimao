package br.com.teatime.mimao.service;

import br.com.teatime.mimao.entity.Professor;
import br.com.teatime.mimao.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(String id) {
        return professorRepository.findById(id).orElse(null);
    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public void delete(String id) {
        professorRepository.deleteById(id);
    }
}
