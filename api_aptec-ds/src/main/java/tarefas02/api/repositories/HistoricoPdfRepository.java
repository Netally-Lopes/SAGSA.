package tarefas02.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tarefas02.api.models.HistoricoPdf;

import java.util.List;

@Repository
public interface HistoricoPdfRepository extends JpaRepository<HistoricoPdf, Long> {

    List<HistoricoPdf> findBySituacaoAprendizagemId(Long situacaoAprendizagemId);
}