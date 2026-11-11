package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.Curso;
import tarefas02.api.repositories.CursoRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso findById(Long id) {
        Optional<Curso> obj = this.cursoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Curso não encontrado! Id: " + id + ", Tipo: " + Curso.class.getName()));
    }

    public List<Curso> findAll() {
        return this.cursoRepository.findAll();
    }

    public Curso create(Curso obj) {
        obj.setId(null);
        return this.cursoRepository.save(obj);
    }

    public Curso update(Curso obj) {
        findById(obj.getId());
        return this.cursoRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.cursoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir este curso pois há registros associados!");
        }
    }
}