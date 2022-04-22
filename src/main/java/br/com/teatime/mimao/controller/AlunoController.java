package br.com.teatime.mimao.controller;

import br.com.teatime.mimao.entity.Aluno;
import br.com.teatime.mimao.entity.PeriodoTreino;
import br.com.teatime.mimao.repository.AlunoRepository;
import br.com.teatime.mimao.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Aluno> findAll() {
        return alunoService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Aluno findById(@PathVariable String id) {
        return alunoService.findById(id);
    }

    @RequestMapping(value = "/alunoTo", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Aluno findByIdTo() {
        return new Aluno("Madison");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Aluno create(@RequestBody Aluno aluno) {
        aluno.setId(null);
        return alunoService.save(aluno);
    }

    @RequestMapping(value = "/historicoTreinoAluno/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Aluno createHistoricoTreinoAluno(@PathVariable String id, @RequestBody PeriodoTreino historicoTreino) {
        return alunoService.saveTreinoAluno(id, historicoTreino);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Aluno update(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable String id) {
        alunoService.delete(id);
    }
}