package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.RecursoAmbiente;
import tarefas02.api.services.RecursoAmbienteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recursos-ambiente")
public class RecursoAmbienteController {

    private final RecursoAmbienteService recursoAmbienteService;

    public RecursoAmbienteController(RecursoAmbienteService recursoAmbienteService) {
        this.recursoAmbienteService = recursoAmbienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoAmbiente> findById(@PathVariable Long id) {
        RecursoAmbiente obj = this.recursoAmbienteService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<RecursoAmbiente>> findAll() {
        List<RecursoAmbiente> list = this.recursoAmbienteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RecursoAmbiente obj) {
        RecursoAmbiente newObj = this.recursoAmbienteService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody RecursoAmbiente obj, @PathVariable Long id) {
        obj.setId(id);
        this.recursoAmbienteService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.recursoAmbienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}