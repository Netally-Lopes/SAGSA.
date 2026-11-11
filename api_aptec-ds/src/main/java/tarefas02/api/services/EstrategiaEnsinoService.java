package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.EstrategiaEnsino;
import tarefas02.api.repositories.EstrategiaEnsinoRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class EstrategiaEnsinoService {

    private final EstrategiaEnsinoRepository estrategiaEnsinoRepository;

    public EstrategiaEnsinoService(EstrategiaEnsinoRepository estrategiaEnsinoRepository) {
        this.estrategiaEnsinoRepository = estrategiaEnsinoRepository;
    }

    public EstrategiaEnsino findById(Long id) {
        Optional<EstrategiaEnsino> obj = this.estrategiaEnsinoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Estratégia de Ensino não encontrada! Id: " + id + ", Tipo: " + EstrategiaEnsino.class.getName()));
    }

    public List<EstrategiaEnsino> findAll() {
        return this.estrategiaEnsinoRepository.findAll();
    }

    public EstrategiaEnsino create(EstrategiaEnsino obj) {
        obj.setId(null);
        return this.estrategiaEnsinoRepository.save(obj);
    }

    public EstrategiaEnsino update(EstrategiaEnsino obj) {
        findById(obj.getId());
        return this.estrategiaEnsinoRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.estrategiaEnsinoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta estratégia de ensino pois há registros associados!");
        }
    }
}