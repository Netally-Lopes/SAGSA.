package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.Funcao;
import tarefas02.api.repositories.FuncaoRepository;
import tarefas02.api.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class FuncaoService {

    private final FuncaoRepository funcaoRepository;

    public FuncaoService(FuncaoRepository funcaoRepository) {
        this.funcaoRepository = funcaoRepository;
    }

    public Funcao findById(Long id) {
        return this.funcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Função não encontrada! Id: " + id));
    }

    public List<Funcao> findAll() {
        return this.funcaoRepository.findAll();
    }

    public Funcao create(Funcao obj) {
        obj.setId(null);
        return this.funcaoRepository.save(obj);
    }

    public Funcao update(Funcao obj) {
        findById(obj.getId());
        return this.funcaoRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        this.funcaoRepository.deleteById(id);
    }
}