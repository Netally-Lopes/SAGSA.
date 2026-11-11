package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.Turma;
import tarefas02.api.repositories.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional(readOnly = true)
    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Turma> listarAtivas() {
        return turmaRepository.findByStatusTrue();
    }

    @Transactional(readOnly = true)
    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Turma> listarPorPlanoCurso(Long planoCursoId) {
        return turmaRepository.findByPlanoCursoId(planoCursoId);
    }

    @Transactional
    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);
    }

    @Transactional
    public void deletar(Long id) {
        Turma turma = buscarPorId(id);
        turmaRepository.delete(turma);
    }
}