package br.com.teatime.mimao.controller;

import br.com.teatime.mimao.entity.Exercicio;
import br.com.teatime.mimao.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Exercicio> findAll() {
        return exercicioService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Exercicio findById(@PathVariable String id) {
        return exercicioService.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Exercicio create(@RequestBody Exercicio exercicio) {
        exercicio.setId(null);
        return exercicioService.save(exercicio);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Exercicio update(@RequestBody Exercicio exercicio) {
        return exercicioService.save(exercicio);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable String id) {
        exercicioService.delete(id);
    }
}