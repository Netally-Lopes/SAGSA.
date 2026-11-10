package tarefas02.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarefas02.api.models.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByEscolaId(Long escolaId);
    List<Turma> findByPlanoCursoId(Long planoCursoId);
    List<Turma> findByAtivoTrue();
}                                       