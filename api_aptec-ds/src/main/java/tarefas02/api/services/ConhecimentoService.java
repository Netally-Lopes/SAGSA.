package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.Conhecimento;
import tarefas02.api.repositories.ConhecimentoRepository;
import tarefas02.api.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class ConhecimentoService {

    private final ConhecimentoRepository conhecimentoRepository;

    public ConhecimentoService(ConhecimentoRepository conhecimentoRepository) {
        this.conhecimentoRepository = conhecimentoRepository;
    }

    public Conhecimento findById(Long id) {
        return this.conhecimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conhecimento não encontrado! Id: " + id));
    }

    public List<Conhecimento> findAll() {
        return this.conhecimentoRepository.findAll();
    }

    public Conhecimento create(Conhecimento obj) {
        obj.setId(null);
        return this.conhecimentoRepository.save(obj);
    }

    public Conhecimento update(Conhecimento obj) {
        Conhecimento newObj = findById(obj.getId());
        newObj.setDescricao(obj.getDescricao());
        newObj.setUnidadeCurricular(obj.getUnidadeCurricular());
        return this.conhecimentoRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        this.conhecimentoRepository.deleteById(id);
    }
}