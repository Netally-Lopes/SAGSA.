package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.Subfuncao;
import tarefas02.api.repositories.SubfuncaoRepository;
import tarefas02.api.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class SubfuncaoService {

    private final SubfuncaoRepository subfuncaoRepository;

    public SubfuncaoService(SubfuncaoRepository subfuncaoRepository) {
        this.subfuncaoRepository = subfuncaoRepository;
    }

    public Subfuncao findById(Long id) {
        return this.subfuncaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subfunção não encontrada! Id: " + id));
    }

    public List<Subfuncao> findAll() {
        return this.subfuncaoRepository.findAll();
    }

    public Subfuncao create(Subfuncao obj) {
        obj.setId(null);
        return this.subfuncaoRepository.save(obj);
    }

    public Subfuncao update(Subfuncao obj) {
        findById(obj.getId());
        return this.subfuncaoRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        this.subfuncaoRepository.deleteById(id);
    }
}