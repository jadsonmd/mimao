package br.com.teatime.mimao.controller;

import br.com.teatime.mimao.entity.Aluno;
import br.com.teatime.mimao.entity.Ficha;
import br.com.teatime.mimao.service.FichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("ficha")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @RequestMapping(value = "/{idProfessor}/{idAluno}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Ficha> findAll(@PathVariable String idProfessor, @PathVariable String idAluno) {
        return fichaService.findAll(idProfessor, idAluno);
    }

    @RequestMapping(value = "/{idProfessor}/{idAluno}/{idFicha}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Ficha findById(@PathVariable String idProfessor, @PathVariable String idAluno, @PathVariable String idFicha) {
        return fichaService.findById(idProfessor, idAluno, idFicha);
    }

    @RequestMapping(value = "/{idProfessor}/{idAluno}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Ficha create(@PathVariable String idProfessor, @PathVariable String idAluno, @RequestBody Ficha ficha) {
        ficha.setId(null);
        return fichaService.create(idProfessor, idAluno, ficha);
    }

    @RequestMapping(value = "/{idProfessor}/{idAluno}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Ficha update(@PathVariable String idProfessor, @PathVariable String idAluno, @RequestBody Ficha ficha) {
        return fichaService.update(idProfessor, idAluno, ficha);
    }
}
