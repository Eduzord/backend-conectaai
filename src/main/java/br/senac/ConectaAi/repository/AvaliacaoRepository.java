package br.senac.ConectaAi.repository;

import br.senac.ConectaAi.entity.Avaliacao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    @Query("SELECT a FROM Avaliacao a WHERE a.status >=0")
    List<Avaliacao> listarAvaliacoesAtivas();

    @Query("SELECT a FROM Avaliacao a WHERE a.id = :id AND a.status >=0")
    Avaliacao obterAvaliacaoAtivaPorId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Avaliacao a SET a.status = -1 WHERE a.id = :id")
    int apagadorLogico(@Param("id") int id);
}
