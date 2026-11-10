package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.SituacaoAprendizagem;
import tarefas02.api.repositories.SituacaoAprendizagemRepository;

@Service
public class SituacaoAprendizagemService {

    @Autowired
    private SituacaoAprendizagemRepository situacaoAprendizagemRepository;

    @Transactional(readOnly = true)
    public List<SituacaoAprendizagem> listarTodas() {
        return situacaoAprendizagemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SituacaoAprendizagem buscarPorId(Long id) {
        return situacaoAprendizagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Situação de Aprendizagem não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<SituacaoAprendizagem> listarPorUnidadeCurricular(Long ucId) {
        return situacaoAprendizagemRepository.findByUnidadeCurricularId(ucId);
    }

    @Transactional(readOnly = true)
    public List<SituacaoAprendizagem> listarPublicas() {
        return situacaoAprendizagemRepository.findByPublicoTrue();
    }

    @Transactional
    public SituacaoAprendizagem salvar(SituacaoAprendizagem sa) {
        return situacaoAprendizagemRepository.save(sa);
    }

    @Transactional
    public void deletar(Long id) {
        SituacaoAprendizagem sa = buscarPorId(id);
        situacaoAprendizagemRepository.delete(sa);
    }
}