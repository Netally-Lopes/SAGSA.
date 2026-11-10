package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.RecursoAmbiente;
import tarefas02.api.repositories.RecursoAmbienteRepository;

@Service
public class RecursoAmbienteService {

    @Autowired
    private RecursoAmbienteRepository recursoAmbienteRepository;

    @Transactional(readOnly = true)
    public List<RecursoAmbiente> listarTodos() {
        return recursoAmbienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RecursoAmbiente buscarPorId(Long id) {
        return recursoAmbienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso/Ambiente não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<RecursoAmbiente> listarPorSituacaoAprendizagem(Long saId) {
        return recursoAmbienteRepository.findBySituacaoAprendizagemId(saId);
    }

    @Transactional
    public RecursoAmbiente salvar(RecursoAmbiente recurso) {
        return recursoAmbienteRepository.save(recurso);
    }

    @Transactional
    public void deletar(Long id) {
        RecursoAmbiente recurso = buscarPorId(id);
        recursoAmbienteRepository.delete(recurso);
    }
}