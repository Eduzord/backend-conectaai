package br.senac.ConectaAi.repository;


import br.senac.ConectaAi.entity.HistoricoVisualizacoes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoVisualizacoesRepository extends JpaRepository<HistoricoVisualizacoes, Integer> {

    @Query("SELECT h FROM HistoricoVisualizacoes h WHERE h.status >=0")
    List<HistoricoVisualizacoes> listarHistoricosAtivos();

    @Query("SELECT h FROM HistoricoVisualizacoes h WHERE h.id = :id AND h.status >=0")
    HistoricoVisualizacoes obterHistoricoVisualizacoesPorId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE HistoricoVisualizacoes h SET h.status = -1 WHERE h.id = :id")
    int apagadorLogico(@Param("id") int id);

}
