package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.PadraoDesempenho;
import tarefas02.api.repositories.PadraoDesempenhoRepository;
import tarefas02.api.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class PadraoDesempenhoService {

    private final PadraoDesempenhoRepository padraoDesempenhoRepository;

    public PadraoDesempenhoService(PadraoDesempenhoRepository padraoDesempenhoRepository) {
        this.padraoDesempenhoRepository = padraoDesempenhoRepository;
    }

    public PadraoDesempenho findById(Long id) {
        return this.padraoDesempenhoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Padrão de Desempenho não encontrado! Id: " + id));
    }

    public List<PadraoDesempenho> findAll() {
        return this.padraoDesempenhoRepository.findAll();
    }

    public PadraoDesempenho create(PadraoDesempenho obj) {
        obj.setId(null);
        return this.padraoDesempenhoRepository.save(obj);
    }

    public PadraoDesempenho update(PadraoDesempenho obj) {
        findById(obj.getId());
        return this.padraoDesempenhoRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        this.padraoDesempenhoRepository.deleteById(id);
    }
}