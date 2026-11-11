package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.Funcao;
import tarefas02.api.services.FuncaoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcoes")
public class FuncaoController {

    private final FuncaoService funcaoService;

    public FuncaoController(FuncaoService funcaoService) {
        this.funcaoService = funcaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcao> findById(@PathVariable Long id) {
        Funcao obj = this.funcaoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Funcao>> findAll() {
        List<Funcao> list = this.funcaoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Funcao obj) {
        Funcao newObj = this.funcaoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Funcao obj, @PathVariable Long id) {
        obj.setId(id);
        this.funcaoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.funcaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}