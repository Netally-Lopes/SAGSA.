package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.Capacidade;
import tarefas02.api.services.CapacidadeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/capacidades")
public class CapacidadeController {

    private final CapacidadeService capacidadeService;

    public CapacidadeController(CapacidadeService capacidadeService) {
        this.capacidadeService = capacidadeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Capacidade> findById(@PathVariable Long id) {
        Capacidade obj = this.capacidadeService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Capacidade>> findAll() {
        List<Capacidade> list = this.capacidadeService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Capacidade obj) {
        Capacidade newObj = this.capacidadeService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Capacidade obj, @PathVariable Long id) {
        obj.setId(id);
        this.capacidadeService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.capacidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
