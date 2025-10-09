package br.senac.ConectaAi.repository;

import br.senac.ConectaAi.entity.Ferramenta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FerramentaRepository extends JpaRepository<Ferramenta, Integer> {

    @Query("SELECT f FROM Ferramenta f WHERE f.status >=0")
    List<Ferramenta> listarFerramentasAtivas();

    @Query("SELECT f FROM Ferramenta f WHERE f.id = :id AND f.status >=0")
    Ferramenta obterFerramentaAtivaPorId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Ferramenta f SET f.status = -1 WHERE f.id = :id")
    int apagadorLogico(@Param("id") int id);
}
