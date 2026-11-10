package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.CriterioAvaliacao;
import tarefas02.api.repositories.CriterioAvaliacaoRepository;

@Service
public class CriterioAvaliacaoService {

    @Autowired
    private CriterioAvaliacaoRepository criterioAvaliacaoRepository;

    @Transactional(readOnly = true)
    public List<CriterioAvaliacao> listarTodos() {
        return criterioAvaliacaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CriterioAvaliacao buscarPorId(Long id) {
        return criterioAvaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Critério de Avaliação não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<CriterioAvaliacao> listarPorInstrumento(Long instrumentoId) {
        return criterioAvaliacaoRepository.findByInstrumentoAvaliacaoId(instrumentoId);
    }

    @Transactional
    public CriterioAvaliacao salvar(CriterioAvaliacao criterio) {
        return criterioAvaliacaoRepository.save(criterio);
    }

    @Transactional
    public void deletar(Long id) {
        CriterioAvaliacao criterio = buscarPorId(id);
        criterioAvaliacaoRepository.delete(criterio);
    }
}