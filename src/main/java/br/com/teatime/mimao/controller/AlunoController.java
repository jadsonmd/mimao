package br.com.teatime.mimao.controller;

import br.com.teatime.mimao.entity.Aluno;
import br.com.teatime.mimao.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @RequestMapping(value = "/{idProfessor}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Set<Aluno> findAll(@PathVariable String idProfessor) {
        return alunoService.findAll(idProfessor);
    }

    @RequestMapping(value = "/{idProfessor}/{idAluno}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Aluno findById(@PathVariable String idProfessor, @PathVariable String idAluno) {
        return alunoService.findById(idProfessor, idAluno);
    }

    @RequestMapping(value = "/alunoTo", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Aluno findByIdTo() {
        // Teste sem usar o banco.
        return new Aluno("jadsonmd");
    }

    @RequestMapping(value = "{idProfessor}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Aluno create(@PathVariable String idProfessor, @RequestBody Aluno aluno) {
        aluno.setId(null);
        return alunoService.create(idProfessor, aluno);
    }

    @RequestMapping(value = "{idProfessor}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Aluno update(@PathVariable String idProfessor, @RequestBody Aluno aluno) {
        return alunoService.update(idProfessor, aluno);
    }

    @RequestMapping(value = "/{idProfessor}/{idAluno}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable String idProfessor, @PathVariable String idAluno) {
        alunoService.delete(idProfessor, idAluno);
    }
}
