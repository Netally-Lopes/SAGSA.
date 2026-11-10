package tarefas02.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarefas02.api.models.RecursoAmbiente;

@Repository
public interface RecursoAmbienteRepository extends JpaRepository<RecursoAmbiente, Long> {
    List<RecursoAmbiente> findBySituacaoAprendizagemId(Long situacaoAprendizagemId);
}