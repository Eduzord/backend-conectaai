package br.senac.ConectaAi.repository;


import br.senac.ConectaAi.entity.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("SELECT c FROM Categoria c WHERE c.status >=0")
    List<Categoria> listarCategoriasAtivas();

    @Query("SELECT c FROM Categoria c WHERE c.id = :id AND c.status >=0")
    Categoria obterCategoriaAtivaPorId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Categoria c SET c.status = -1 WHERE c.id = :id")
    int apagadorLogico(@Param("id") int id);
}