package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.UnidadeCurricular;
import tarefas02.api.repositories.UnidadeCurricularRepository;

@Service
public class UnidadeCurricularService {

    @Autowired
    private UnidadeCurricularRepository unidadeCurricularRepository;

    @Transactional(readOnly = true)
    public List<UnidadeCurricular> listarTodas() {
        return unidadeCurricularRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UnidadeCurricular buscarPorId(Long id) {
        return unidadeCurricularRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade Curricular não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<UnidadeCurricular> listarPorModulo(Long moduloId) {
        return unidadeCurricularRepository.findByModuloId(moduloId);
    }

    @Transactional
    public UnidadeCurricular salvar(UnidadeCurricular uc) {
        return unidadeCurricularRepository.save(uc);
    }

    @Transactional
    public void deletar(Long id) {
        UnidadeCurricular uc = buscarPorId(id);
        unidadeCurricularRepository.delete(uc);
    }
}