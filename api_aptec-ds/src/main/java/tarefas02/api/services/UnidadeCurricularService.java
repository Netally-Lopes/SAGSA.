package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.UnidadeCurricular;
import tarefas02.api.repositories.UnidadeCurricularRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeCurricularService {

    private final UnidadeCurricularRepository unidadeCurricularRepository;

    public UnidadeCurricularService(UnidadeCurricularRepository unidadeCurricularRepository) {
        this.unidadeCurricularRepository = unidadeCurricularRepository;
    }

    public UnidadeCurricular findById(Long id) {
        Optional<UnidadeCurricular> obj = this.unidadeCurricularRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Unidade Curricular não encontrada! Id: " + id + ", Tipo: " + UnidadeCurricular.class.getName()));
    }

    public List<UnidadeCurricular> findAll() {
        return this.unidadeCurricularRepository.findAll();
    }

    public UnidadeCurricular create(UnidadeCurricular obj) {
        obj.setId(null);
        return this.unidadeCurricularRepository.save(obj);
    }

    public UnidadeCurricular update(UnidadeCurricular obj) {
        findById(obj.getId());
        return this.unidadeCurricularRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.unidadeCurricularRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta unidade curricular pois há registros associados!");
        }
    }
}