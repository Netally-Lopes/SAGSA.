package tarefas02.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.Capacidade;
import tarefas02.api.repositories.CapacidadeRepository;

@Service
public class CapacidadeService {

    @Autowired
    private CapacidadeRepository capacidadeRepository;

    @Transactional(readOnly = true)
    public List<Capacidade> listarTodas() {
        return capacidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Capacidade buscarPorId(Long id) {
        return capacidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Capacidade não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Capacidade> listarPorUnidadeCurricular(Long ucId) {
        return capacidadeRepository.findByUnidadeCurricularId(ucId);
    }

    @Transactional
    public Capacidade salvar(Capacidade capacidade) {
        return capacidadeRepository.save(capacidade);
    }

    @Transactional
    public void deletar(Long id) {
        Capacidade capacidade = buscarPorId(id);
        capacidadeRepository.delete(capacidade);
    }
}