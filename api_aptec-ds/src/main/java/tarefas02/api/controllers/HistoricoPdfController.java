package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.HistoricoPdf;
import tarefas02.api.services.HistoricoPdfService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/historico-pdf")
public class HistoricoPdfController {

    private final HistoricoPdfService historicoPdfService;

    public HistoricoPdfController(HistoricoPdfService historicoPdfService) {
        this.historicoPdfService = historicoPdfService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoPdf> findById(@PathVariable Long id) {
        HistoricoPdf obj = this.historicoPdfService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<HistoricoPdf>> findAll() {
        List<HistoricoPdf> list = this.historicoPdfService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/situacao-aprendizagem/{situacaoId}")
    public ResponseEntity<List<HistoricoPdf>> findBySituacaoAprendizagem(@PathVariable Long situacaoId) {
        List<HistoricoPdf> list = this.historicoPdfService.findBySituacaoAprendizagem(situacaoId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody HistoricoPdf obj) {
        HistoricoPdf newObj = this.historicoPdfService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.historicoPdfService.delete(id);
        return ResponseEntity.noContent().build();
    }
}