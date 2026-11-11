package tarefas02.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas02.api.models.AreaTecnologica;
import tarefas02.api.services.AreaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaTecnologica> findById(@PathVariable Long id) {
        AreaTecnologica obj = this.areaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<AreaTecnologica>> findAll() {
        List<AreaTecnologica> list = this.areaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AreaTecnologica obj) {
        AreaTecnologica newObj = this.areaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody AreaTecnologica obj, @PathVariable Long id) {
        obj.setId(id);
        this.areaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.areaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}