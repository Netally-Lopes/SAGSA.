package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.Capacidade;
import tarefas02.api.repositories.CapacidadeRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CapacidadeService {

    private final CapacidadeRepository capacidadeRepository;

    public CapacidadeService(CapacidadeRepository capacidadeRepository) {
        this.capacidadeRepository = capacidadeRepository;
    }

    public Capacidade findById(Long id) {
        Optional<Capacidade> obj = this.capacidadeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Capacidade não encontrada! Id: " + id + ", Tipo: " + Capacidade.class.getName()));
    }

    public List<Capacidade> findAll() {
        return this.capacidadeRepository.findAll();
    }

    public Capacidade create(Capacidade obj) {
        obj.setId(null);
        return this.capacidadeRepository.save(obj);
    }

    public Capacidade update(Capacidade obj) {
        findById(obj.getId());
        return this.capacidadeRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.capacidadeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta capacidade pois há registros associados!");
        }
    }
}