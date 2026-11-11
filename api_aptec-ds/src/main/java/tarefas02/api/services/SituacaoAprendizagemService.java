package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.SituacaoAprendizagem;
import tarefas02.api.repositories.SituacaoAprendizagemRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class SituacaoAprendizagemService {

    private final SituacaoAprendizagemRepository situacaoAprendizagemRepository;

    public SituacaoAprendizagemService(SituacaoAprendizagemRepository situacaoAprendizagemRepository) {
        this.situacaoAprendizagemRepository = situacaoAprendizagemRepository;
    }

    public SituacaoAprendizagem findById(Long id) {
        Optional<SituacaoAprendizagem> obj = this.situacaoAprendizagemRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Situação de Aprendizagem não encontrada! Id: " + id + ", Tipo: " + SituacaoAprendizagem.class.getName()));
    }

    public List<SituacaoAprendizagem> findAll() {
        return this.situacaoAprendizagemRepository.findAll();
    }

    public SituacaoAprendizagem create(SituacaoAprendizagem obj) {
        obj.setId(null);
        return this.situacaoAprendizagemRepository.save(obj);
    }

    public SituacaoAprendizagem update(SituacaoAprendizagem obj) {
        findById(obj.getId());
        return this.situacaoAprendizagemRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.situacaoAprendizagemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta situação de aprendizagem pois há registros associados!");
        }
    }
}