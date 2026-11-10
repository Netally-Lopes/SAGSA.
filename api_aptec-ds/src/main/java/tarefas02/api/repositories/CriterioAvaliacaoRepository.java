package tarefas02.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarefas02.api.models.CriterioAvaliacao;

@Repository
public interface CriterioAvaliacaoRepository extends JpaRepository<CriterioAvaliacao, Long> {
    List<CriterioAvaliacao> findByInstrumentoAvaliacaoId(Long instrumentoAvaliacaoId);
    List<CriterioAvaliacao> findByCapacidadeId(Long capacidadeId);
}