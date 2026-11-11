package tarefas02.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import tarefas02.api.models.Perfil;
import tarefas02.api.services.PerfilService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> findById(@PathVariable Long id) {
        Perfil obj = this.perfilService.buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> findAll() {
        List<Perfil> list = this.perfilService.listarTodos();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> create(@Valid @RequestBody Perfil obj) {
        Perfil perfilSalvo = this.perfilService.salvar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(perfilSalvo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> update(@Valid @RequestBody Perfil obj, @PathVariable Long id) {
        obj.setId(id);
        this.perfilService.salvar(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.perfilService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}