package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.PlanoCurso;
import tarefas02.api.repositories.PlanoCursoRepository;

@Service
public class PlanoCursoService {

    @Autowired
    private PlanoCursoRepository planoCursoRepository;

    @Transactional(readOnly = true)
    public List<PlanoCurso> listarTodos() {
        return planoCursoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PlanoCurso buscarPorId(Long id) {
        return planoCursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano de Curso não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<PlanoCurso> listarPorCurso(Long cursoId) {
        return planoCursoRepository.findByCursoId(cursoId);
    }

    @Transactional
    public PlanoCurso salvar(PlanoCurso plano) {
        return planoCursoRepository.save(plano);
    }

    @Transactional
    public void deletar(Long id) {
        PlanoCurso plano = buscarPorId(id);
        planoCursoRepository.delete(plano);
    }
}