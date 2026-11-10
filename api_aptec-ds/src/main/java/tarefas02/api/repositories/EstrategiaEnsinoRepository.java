package tarefas02.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarefas02.api.models.EstrategiaEnsino;

@Repository
public interface EstrategiaEnsinoRepository extends JpaRepository<EstrategiaEnsino, Long> {
    List<EstrategiaEnsino> findBySituacaoAprendizagemId(Long situacaoAprendizagemId);
}