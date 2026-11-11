package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.Modulo;
import tarefas02.api.repositories.ModuloRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepository;

    public ModuloService(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }

    public Modulo findById(Long id) {
        Optional<Modulo> obj = this.moduloRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Módulo não encontrado! Id: " + id + ", Tipo: " + Modulo.class.getName()));
    }

    public List<Modulo> findAll() {
        return this.moduloRepository.findAll();
    }

    public Modulo create(Modulo obj) {
        obj.setId(null);
        return this.moduloRepository.save(obj);
    }

    public Modulo update(Modulo obj) {
        findById(obj.getId());
        return this.moduloRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.moduloRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir este módulo pois há registros associados!");
        }
    }
}