package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.EstrategiaEnsino;
import tarefas02.api.services.EstrategiaEnsinoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estrategias-ensino")
public class EstrategiaEnsinoController {

    private final EstrategiaEnsinoService estrategiaEnsinoService;

    public EstrategiaEnsinoController(EstrategiaEnsinoService estrategiaEnsinoService) {
        this.estrategiaEnsinoService = estrategiaEnsinoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstrategiaEnsino> findById(@PathVariable Long id) {
        EstrategiaEnsino obj = this.estrategiaEnsinoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<EstrategiaEnsino>> findAll() {
        List<EstrategiaEnsino> list = this.estrategiaEnsinoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EstrategiaEnsino obj) {
        EstrategiaEnsino newObj = this.estrategiaEnsinoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody EstrategiaEnsino obj, @PathVariable Long id) {
        obj.setId(id);
        this.estrategiaEnsinoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.estrategiaEnsinoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}