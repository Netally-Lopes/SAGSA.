package tarefas02.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tarefas02.api.models.Turma;
import tarefas02.api.services.TurmaService;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    // GET /api/turmas
    @GetMapping
    public ResponseEntity<List<Turma>> listarTodas() {
        List<Turma> turmas = turmaService.listarTodas();
        return ResponseEntity.ok(turmas);
    }

    // GET /api/turmas/ativas
    @GetMapping("/ativas")
    public ResponseEntity<List<Turma>> listarAtivas() {
        List<Turma> turmasAtivas = turmaService.listarAtivas();
        return ResponseEntity.ok(turmasAtivas);
    }

    // GET /api/turmas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        Turma turma = turmaService.buscarPorId(id);
        return ResponseEntity.ok(turma);
    }

    // GET /api/turmas/plano-curso/{planoCursoId}
    @GetMapping("/plano-curso/{planoCursoId}")
    public ResponseEntity<List<Turma>> listarPorPlanoCurso(@PathVariable Long planoCursoId) {
        List<Turma> turmas = turmaService.listarPorPlanoCurso(planoCursoId);
        return ResponseEntity.ok(turmas);
    }

    // POST /api/turmas
    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody Turma turma) {
        Turma turmaSalva = turmaService.salvar(turma);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(turmaSalva.getId())
                .toUri();
                
        return ResponseEntity.created(location).body(turmaSalva);
    }

    // PUT /api/turmas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        // Garante que a turma existe antes de atualizar (lança exceção se não existir)
        turmaService.buscarPorId(id);
        
        // Certifique-se de que sua entidade Turma possui o método setId(Long id)
        turma.setId(id);
        Turma turmaAtualizada = turmaService.salvar(turma);
        
        return ResponseEntity.ok(turmaAtualizada);
    }

    // DELETE /api/turmas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}