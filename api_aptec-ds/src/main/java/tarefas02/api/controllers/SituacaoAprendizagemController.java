package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.SituacaoAprendizagem;
import tarefas02.api.services.SituacaoAprendizagemService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/situacoes-aprendizagem")
public class SituacaoAprendizagemController {

    private final SituacaoAprendizagemService situacaoAprendizagemService;

    public SituacaoAprendizagemController(SituacaoAprendizagemService situacaoAprendizagemService) {
        this.situacaoAprendizagemService = situacaoAprendizagemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoAprendizagem> findById(@PathVariable Long id) {
        SituacaoAprendizagem obj = this.situacaoAprendizagemService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<SituacaoAprendizagem>> findAll() {
        List<SituacaoAprendizagem> list = this.situacaoAprendizagemService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody SituacaoAprendizagem obj) {
        SituacaoAprendizagem newObj = this.situacaoAprendizagemService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody SituacaoAprendizagem obj, @PathVariable Long id) {
        obj.setId(id);
        this.situacaoAprendizagemService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.situacaoAprendizagemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}