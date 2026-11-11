package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.CriterioRubrica;
import tarefas02.api.services.CriterioRubricaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rubricas")
public class CriterioRubricaController {

    private final CriterioRubricaService criterioRubricaService;

    public CriterioRubricaController(CriterioRubricaService criterioRubricaService) {
        this.criterioRubricaService = criterioRubricaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriterioRubrica> findById(@PathVariable Long id) {
        CriterioRubrica obj = this.criterioRubricaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CriterioRubrica>> findAll() {
        List<CriterioRubrica> list = this.criterioRubricaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CriterioRubrica obj) {
        CriterioRubrica newObj = this.criterioRubricaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody CriterioRubrica obj, @PathVariable Long id) {
        obj.setId(id);
        this.criterioRubricaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.criterioRubricaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}