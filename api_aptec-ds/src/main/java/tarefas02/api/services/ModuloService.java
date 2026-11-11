package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.Modulo;
import tarefas02.api.repositories.ModuloRepository;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    @Transactional(readOnly = true)
    public List<Modulo> listarTodos() {
        return moduloRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Modulo buscarPorId(Long id) {
        return moduloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Módulo não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Modulo> listarPorPlanoCurso(Long planoCursoId) {
        return moduloRepository.findByPlanoCursoId(planoCursoId);
    }

    @Transactional
    public Modulo salvar(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    @Transactional
    public void deletar(Long id) {
        Modulo modulo = buscarPorId(id);
        moduloRepository.delete(modulo);
    }
}