package Game.friends.GameFriends.repository;

import Game.friends.GameFriends.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByLogin(String login);
    Optional<UsuarioEntity> findByEmail(String email);

    @Query("SELECT u FROM Usuario u JOIN u.cargos c WHERE LOWER(c.nome) = LOWER(:nomeCargo)")
    Page<UsuarioEntity> findAllByCargoNome(@Param("nomeCargo") String nomeCargo, Pageable pageable);

    @Query("""
    SELECT u FROM Usuario u
    JOIN u.cargos c
    WHERE LOWER(c.nome) = LOWER(:nomeCargo)
""")
    Page<UsuarioEntity> findByCargo(@Param("nomeCargo") String nomeCargo, Pageable pageable);

}
