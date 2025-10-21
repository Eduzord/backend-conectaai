package br.senac.ConectaAi.repository;

import br.senac.ConectaAi.entity.Favorito;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    @Query("SELECT f FROM Favorito f WHERE f.status >=0")
    List<Favorito> listarFavoritosAtivos();

    @Query("SELECT f FROM Favorito f WHERE f.id = :id AND f.status >=0")
    Favorito obterFavoritoAtivoPorId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Favorito f SET f.status = -1 WHERE f.id = :id")
    int apagadorLogico(@Param("id") int id);
}
