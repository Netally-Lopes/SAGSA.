package tarefas02.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarefas02.api.models.Escola;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Long> {

    // Busca apenas as escolas ativas no sistema
    List<Escola> findByAtivoTrue();

    // Busca uma escola pelo código da unidade (ex: "SENAI-SL")
    Optional<Escola> findByCodigoUnidade(String codigoUnidade);

    // Verifica se já existe uma escola cadastrada com esse código
    boolean existsByCodigoUnidade(String codigoUnidade);
}