package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.Conhecimento;
import tarefas02.api.services.ConhecimentoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/conhecimentos")
public class ConhecimentoController {

    private final ConhecimentoService conhecimentoService;

    public ConhecimentoController(ConhecimentoService conhecimentoService) {
        this.conhecimentoService = conhecimentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conhecimento> findById(@PathVariable Long id) {
        Conhecimento obj = this.conhecimentoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Conhecimento>> findAll() {
        List<Conhecimento> list = this.conhecimentoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Conhecimento obj) {
        Conhecimento newObj = this.conhecimentoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Conhecimento obj, @PathVariable Long id) {
        obj.setId(id);
        this.conhecimentoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.conhecimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}