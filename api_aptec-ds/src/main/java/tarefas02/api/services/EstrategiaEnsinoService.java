package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.EstrategiaEnsino;
import tarefas02.api.repositories.EstrategiaEnsinoRepository;

@Service
public class EstrategiaEnsinoService {

    @Autowired
    private EstrategiaEnsinoRepository estrategiaEnsinoRepository;

    @Transactional(readOnly = true)
    public List<EstrategiaEnsino> listarTodas() {
        return estrategiaEnsinoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EstrategiaEnsino buscarPorId(Long id) {
        return estrategiaEnsinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estratégia de Ensino não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<EstrategiaEnsino> listarPorSituacaoAprendizagem(Long saId) {
        return estrategiaEnsinoRepository.findBySituacaoAprendizagemId(saId);
    }

    @Transactional
    public EstrategiaEnsino salvar(EstrategiaEnsino estrategia) {
        return estrategiaEnsinoRepository.save(estrategia);
    }

    @Transactional
    public void deletar(Long id) {
        EstrategiaEnsino estrategia = buscarPorId(id);
        estrategiaEnsinoRepository.delete(estrategia);
    }
}