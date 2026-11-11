package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.RecursoAmbiente;
import tarefas02.api.repositories.RecursoAmbienteRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoAmbienteService {

    private final RecursoAmbienteRepository recursoAmbienteRepository;

    public RecursoAmbienteService(RecursoAmbienteRepository recursoAmbienteRepository) {
        this.recursoAmbienteRepository = recursoAmbienteRepository;
    }

    public RecursoAmbiente findById(Long id) {
        Optional<RecursoAmbiente> obj = this.recursoAmbienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Recurso de Ambiente não encontrado! Id: " + id + ", Tipo: " + RecursoAmbiente.class.getName()));
    }

    public List<RecursoAmbiente> findAll() {
        return this.recursoAmbienteRepository.findAll();
    }

    public RecursoAmbiente create(RecursoAmbiente obj) {
        obj.setId(null);
        return this.recursoAmbienteRepository.save(obj);
    }

    public RecursoAmbiente update(RecursoAmbiente obj) {
        findById(obj.getId());
        return this.recursoAmbienteRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.recursoAmbienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir este recurso de ambiente pois há registros associados!");
        }
    }
}