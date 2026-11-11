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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import tarefas02.api.models.Aluno;
import tarefas02.api.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        Aluno obj = this.alunoService.buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> list = this.alunoService.listarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<Aluno>> findByTurma(@PathVariable Long turmaId) {
        List<Aluno> list = this.alunoService.listarPorTurma(turmaId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/cpf")
    public ResponseEntity<Aluno> findByCpf(@RequestParam String cpf) {
        Aluno obj = this.alunoService.buscarPorCpf(cpf);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Aluno obj) {
        Aluno alunoSalvo = this.alunoService.salvar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(alunoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Aluno obj, @PathVariable Long id) {
        obj.setId(id);
        this.alunoService.salvar(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}