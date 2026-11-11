package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.Turma;
import tarefas02.api.repositories.TurmaRepository;
import tarefas02.api.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public Turma findById(Long id) {
        return this.turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada! Id: " + id));
    }

    public List<Turma> findAll() {
        return this.turmaRepository.findAll();
    }

    public List<Turma> findByCurso(Long cursoId) {
        return this.turmaRepository.findByCursoId(cursoId);
    }

    public List<Turma> findByStatusAtivo() {
        return this.turmaRepository.findByStatusTrue();
    }

    public Turma create(Turma obj) {
        obj.setId(null);
        if (obj.getStatus() == null) {
            obj.setStatus(true);
        }
        return this.turmaRepository.save(obj);
    }

    public Turma update(Turma obj) {
        Turma newObj = findById(obj.getId());
        newObj.setCodigo(obj.getCodigo());
        newObj.setPeriodo(obj.getPeriodo());
        if (obj.getStatus() != null) {
            newObj.setStatus(obj.getStatus());
        }
        if (obj.getCurso() != null) {
            newObj.setCurso(obj.getCurso());
        }
        return this.turmaRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        this.turmaRepository.deleteById(id);
    }
}