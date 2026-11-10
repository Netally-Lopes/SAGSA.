package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.AreaTecnologica;
import tarefas02.api.repositories.AreaRepository;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Transactional(readOnly = true)
    public List<AreaTecnologica> listarTodas() {
        return areaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AreaTecnologica buscarPorId(Long id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área Tecnológica não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<AreaTecnologica> listarPorEixo(Long eixoId) {
        return areaRepository.findByEixoTecnologicoId(eixoId);
    }

    @Transactional
    public AreaTecnologica salvar(AreaTecnologica area) {
        if (area.getEixoTecnologico() != null && areaRepository.existsByNomeAndEixoTecnologicoId(area.getNome(), area.getEixoTecnologico().getId())) {
            throw new RuntimeException("Já existe uma Área Tecnológica com este nome neste Eixo.");
        }
        return areaRepository.save(area);
    }

    @Transactional
    public void deletar(Long id) {
        AreaTecnologica area = buscarPorId(id);
        areaRepository.delete(area);
    }
}