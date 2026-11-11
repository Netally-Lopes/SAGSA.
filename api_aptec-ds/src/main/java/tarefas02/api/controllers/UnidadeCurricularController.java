package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.UnidadeCurricular;
import tarefas02.api.services.UnidadeCurricularService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/unidades-curriculares")
public class UnidadeCurricularController {

    private final UnidadeCurricularService unidadeCurricularService;

    public UnidadeCurricularController(UnidadeCurricularService unidadeCurricularService) {
        this.unidadeCurricularService = unidadeCurricularService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeCurricular> findById(@PathVariable Long id) {
        UnidadeCurricular obj = this.unidadeCurricularService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<UnidadeCurricular>> findAll() {
        List<UnidadeCurricular> list = this.unidadeCurricularService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UnidadeCurricular obj) {
        UnidadeCurricular newObj = this.unidadeCurricularService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UnidadeCurricular obj, @PathVariable Long id) {
        obj.setId(id);
        this.unidadeCurricularService.create(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.unidadeCurricularService.delete(id);
        return ResponseEntity.noContent().build();
    }
}