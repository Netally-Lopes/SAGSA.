
package tarefas02.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tarefas02.api.models.Turma;
import tarefas02.api.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll() {
        return ResponseEntity.ok(turmaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Long id) {
        return ResponseEntity.ok(turmaService.findById(id));
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Turma>> findByCurso(
            @PathVariable Long cursoId) {
        return ResponseEntity.ok(turmaService.findByCurso(cursoId));
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<Turma>> findByStatusAtivo() {
        return ResponseEntity.ok(turmaService.findByStatusAtivo());
    }

    @PostMapping
    public ResponseEntity<Turma> create(@RequestBody Turma turma) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(turmaService.create(turma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(
            @PathVariable Long id,
            @RequestBody Turma turma) {
        turma.setId(id);
        return ResponseEntity.ok(turmaService.update(turma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        turmaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
