package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.EixoTecnologico;
import tarefas02.api.repositories.EixoTecnologicoRepository;
import tarefas02.api.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class EixoTecnologicoService {

    private final EixoTecnologicoRepository eixoTecnologicoRepository;

    public EixoTecnologicoService(EixoTecnologicoRepository eixoTecnologicoRepository) {
        this.eixoTecnologicoRepository = eixoTecnologicoRepository;
    }

    public EixoTecnologico findById(Long id) {
        return this.eixoTecnologicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eixo Tecnológico não encontrado! Id: " + id));
    }

    public List<EixoTecnologico> findAll() {
        return this.eixoTecnologicoRepository.findAll();
    }

    public EixoTecnologico create(EixoTecnologico obj) {
        obj.setId(null);
        return this.eixoTecnologicoRepository.save(obj);
    }

    public EixoTecnologico update(EixoTecnologico obj) {
        EixoTecnologico newObj = findById(obj.getId());
        newObj.setNome(obj.getNome());
        return this.eixoTecnologicoRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        this.eixoTecnologicoRepository.deleteById(id);
    }
}