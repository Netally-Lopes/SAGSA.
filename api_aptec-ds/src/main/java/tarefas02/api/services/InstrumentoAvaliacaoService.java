package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.InstrumentoAvaliacao;
import tarefas02.api.repositories.InstrumentoAvaliacaoRepository;

@Service
public class InstrumentoAvaliacaoService {

    @Autowired
    private InstrumentoAvaliacaoRepository instrumentoAvaliacaoRepository;

    @Transactional(readOnly = true)
    public List<InstrumentoAvaliacao> listarTodos() {
        return instrumentoAvaliacaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public InstrumentoAvaliacao buscarPorId(Long id) {
        return instrumentoAvaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instrumento de Avaliação não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<InstrumentoAvaliacao> listarPorSituacaoAprendizagem(Long saId) {
        return instrumentoAvaliacaoRepository.findBySituacaoAprendizagemId(saId);
    }

    @Transactional
    public InstrumentoAvaliacao salvar(InstrumentoAvaliacao instrumento) {
        return instrumentoAvaliacaoRepository.save(instrumento);
    }

    @Transactional
    public void deletar(Long id) {
        InstrumentoAvaliacao instrumento = buscarPorId(id);
        instrumentoAvaliacaoRepository.delete(instrumento);
    }
}