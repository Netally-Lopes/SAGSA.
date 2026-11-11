package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.Modulo;
import tarefas02.api.services.ModuloService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> findById(@PathVariable Long id) {
        Modulo obj = this.moduloService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Modulo>> findAll() {
        List<Modulo> list = this.moduloService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Modulo obj) {
        Modulo newObj = this.moduloService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Modulo obj, @PathVariable Long id) {
        obj.setId(id);
        this.moduloService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.moduloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}