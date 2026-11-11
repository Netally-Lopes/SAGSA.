package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.HistoricoPdf;
import tarefas02.api.repositories.HistoricoPdfRepository;
import tarefas02.api.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class HistoricoPdfService {

    private final HistoricoPdfRepository historicoPdfRepository;

    public HistoricoPdfService(HistoricoPdfRepository historicoPdfRepository) {
        this.historicoPdfRepository = historicoPdfRepository;
    }

    public HistoricoPdf findById(Long id) {
        return this.historicoPdfRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Histórico de PDF não encontrado! Id: " + id));
    }

    public List<HistoricoPdf> findAll() {
        return this.historicoPdfRepository.findAll();
    }

    public List<HistoricoPdf> findBySituacaoAprendizagem(Long situacaoAprendizagemId) {
        return this.historicoPdfRepository.findBySituacaoAprendizagemId(situacaoAprendizagemId);
    }

    public HistoricoPdf create(HistoricoPdf obj) {
        obj.setId(null);
        return this.historicoPdfRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        this.historicoPdfRepository.deleteById(id);
    }
}