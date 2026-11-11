package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.Perfil;
import tarefas02.api.repositories.PerfilRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public Perfil buscarPorId(Long id) {
        Optional<Perfil> obj = this.perfilRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Perfil não encontrado! Id: " + id + ", Tipo: " + Perfil.class.getName()));
    }

    public List<Perfil> listarTodos() {
        return this.perfilRepository.findAll();
    }

    public Perfil salvar(Perfil obj) {
        if (obj.getId() != null) {
            buscarPorId(obj.getId()); // Garante que existe antes de atualizar
        } else {
            obj.setId(null); // Garante que é um novo registro se não tiver ID
        }
        return this.perfilRepository.save(obj);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        try {
            this.perfilRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir este perfil pois há registros associados!");
        }
    }
}