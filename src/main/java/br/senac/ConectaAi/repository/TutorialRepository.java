package br.senac.ConectaAi.repository;

import br.senac.ConectaAi.entity.Tutorial;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

    @Query("SELECT t FROM Tutorial t WHERE t.status >=0")
    List<Tutorial> listarTutoriaisAtivos();

    @Query("SELECT t FROM Tutorial t WHERE t.id = :id AND t.status >=0")
    Tutorial obterTutorialAtivoPorId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Tutorial t SET t.status = -1 WHERE t.id = :id")
    int apagadorLogico(@Param("id") int id);
}
