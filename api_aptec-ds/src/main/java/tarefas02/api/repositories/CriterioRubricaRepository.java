package tarefas02.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarefas02.api.models.CriterioRubrica;

@Repository
public interface CriterioRubricaRepository extends JpaRepository<CriterioRubrica, Long> {
    List<CriterioRubrica> findByCriterioAvaliacaoId(Long criterioAvaliacaoId);
}