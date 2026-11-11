package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.CriterioRubrica;
import tarefas02.api.repositories.CriterioRubricaRepository;
import tarefas02.api.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class CriterioRubricaService {

    private final CriterioRubricaRepository criterioRubricaRepository;

    public CriterioRubricaService(CriterioRubricaRepository criterioRubricaRepository) {
        this.criterioRubricaRepository = criterioRubricaRepository;
    }

    public CriterioRubrica findById(Long id) {
        return this.criterioRubricaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rubrica não encontrada! Id: " + id));
    }

    public List<CriterioRubrica> findAll() {
        return this.criterioRubricaRepository.findAll();
    }

    public CriterioRubrica create(CriterioRubrica obj) {
        obj.setId(null);
        return this.criterioRubricaRepository.save(obj);
    }

    public CriterioRubrica update(CriterioRubrica obj) {
        findById(obj.getId());
        return this.criterioRubricaRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        this.criterioRubricaRepository.deleteById(id);
    }
}