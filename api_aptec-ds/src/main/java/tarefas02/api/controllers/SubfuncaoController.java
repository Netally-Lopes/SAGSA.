package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.Subfuncao;
import tarefas02.api.services.SubfuncaoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/subfuncoes")
public class SubfuncaoController {

    private final SubfuncaoService subfuncaoService;

    public SubfuncaoController(SubfuncaoService subfuncaoService) {
        this.subfuncaoService = subfuncaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subfuncao> findById(@PathVariable Long id) {
        Subfuncao obj = this.subfuncaoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Subfuncao>> findAll() {
        List<Subfuncao> list = this.subfuncaoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Subfuncao obj) {
        Subfuncao newObj = this.subfuncaoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Subfuncao obj, @PathVariable Long id) {
        obj.setId(id);
        this.subfuncaoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.subfuncaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}