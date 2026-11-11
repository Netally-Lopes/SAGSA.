package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.AreaTecnologica;
import tarefas02.api.repositories.AreaRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public AreaTecnologica findById(Long id) {
        Optional<AreaTecnologica> obj = this.areaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Área Tecnológica não encontrada! Id: " + id + ", Tipo: " + AreaTecnologica.class.getName()));
    }

    public List<AreaTecnologica> findAll() {
        return this.areaRepository.findAll();
    }

    public AreaTecnologica create(AreaTecnologica obj) {
        obj.setId(null);
        return this.areaRepository.save(obj);
    }

    public AreaTecnologica update(AreaTecnologica obj) {
        findById(obj.getId());
        return this.areaRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.areaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta área tecnológica pois há registros associados!");
        }
    }
}