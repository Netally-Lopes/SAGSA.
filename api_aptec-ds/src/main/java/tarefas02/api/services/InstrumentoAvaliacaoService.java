package tarefas02.api.services;

import org.springframework.stereotype.Service;
import tarefas02.api.models.InstrumentoAvaliacao;
import tarefas02.api.repositories.InstrumentoAvaliacaoRepository;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentoAvaliacaoService {

    private final InstrumentoAvaliacaoRepository instrumentoAvaliacaoRepository;

    public InstrumentoAvaliacaoService(InstrumentoAvaliacaoRepository instrumentoAvaliacaoRepository) {
        this.instrumentoAvaliacaoRepository = instrumentoAvaliacaoRepository;
    }

    public InstrumentoAvaliacao findById(Long id) {
        Optional<InstrumentoAvaliacao> obj = this.instrumentoAvaliacaoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Instrumento de Avaliação não encontrado! Id: " + id + ", Tipo: " + InstrumentoAvaliacao.class.getName()));
    }

    public List<InstrumentoAvaliacao> findAll() {
        return this.instrumentoAvaliacaoRepository.findAll();
    }

    public InstrumentoAvaliacao create(InstrumentoAvaliacao obj) {
        obj.setId(null);
        return this.instrumentoAvaliacaoRepository.save(obj);
    }

    public InstrumentoAvaliacao update(InstrumentoAvaliacao obj) {
        findById(obj.getId());
        return this.instrumentoAvaliacaoRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.instrumentoAvaliacaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir este instrumento de avaliação pois há registros associados!");
        }
    }
}