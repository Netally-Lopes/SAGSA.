package tarefas02.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarefas02.api.models.InstrumentoAvaliacao;

@Repository
public interface InstrumentoAvaliacaoRepository extends JpaRepository<InstrumentoAvaliacao, Long> {
    List<InstrumentoAvaliacao> findBySituacaoAprendizagemId(Long situacaoAprendizagemId);
}