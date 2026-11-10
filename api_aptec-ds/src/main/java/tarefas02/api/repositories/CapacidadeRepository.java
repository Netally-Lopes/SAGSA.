package tarefas02.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarefas02.api.models.Capacidade;

@Repository
public interface CapacidadeRepository extends JpaRepository<Capacidade, Long> {
    List<Capacidade> findByUnidadeCurricularId(Long unidadeCurricularId);
    List<Capacidade> findByTipo(String tipo);
}