package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.InstrumentoAvaliacao;
import tarefas02.api.services.InstrumentoAvaliacaoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/instrumentos-avaliacao")
public class InstrumentoAvaliacaoController {

    private final InstrumentoAvaliacaoService instrumentoAvaliacaoService;

    public InstrumentoAvaliacaoController(InstrumentoAvaliacaoService instrumentoAvaliacaoService) {
        this.instrumentoAvaliacaoService = instrumentoAvaliacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentoAvaliacao> findById(@PathVariable Long id) {
        InstrumentoAvaliacao obj = this.instrumentoAvaliacaoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<InstrumentoAvaliacao>> findAll() {
        List<InstrumentoAvaliacao> list = this.instrumentoAvaliacaoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody InstrumentoAvaliacao obj) {
        InstrumentoAvaliacao newObj = this.instrumentoAvaliacaoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody InstrumentoAvaliacao obj, @PathVariable Long id) {
        obj.setId(id);
        this.instrumentoAvaliacaoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.instrumentoAvaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}