package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.Escola;
import tarefas02.api.services.EscolaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/escolas")
public class EscolaController {

    private final EscolaService escolaService;

    public EscolaController(EscolaService escolaService) {
        this.escolaService = escolaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escola> findById(@PathVariable Long id) {
        Escola obj = this.escolaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Escola>> findAll() {
        List<Escola> list = this.escolaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Escola obj) {
        Escola newObj = this.escolaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Escola obj, @PathVariable Long id) {
        obj.setId(id);
        this.escolaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.escolaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}