package Game.friends.GameFriends.repository;

import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioJogoRepository extends JpaRepository<UsuarioJogoEntity, Integer> {
    Optional<UsuarioJogoEntity> findByUsuarios_IdUsuarioAndJogos_IdJogo(Integer idUsuario, Integer idJogo);

    List<UsuarioJogoEntity> findByJogos_IdJogo(Integer idJogo);

    List<UsuarioJogoEntity> findByUsuarios_IdUsuario(Integer idUsuario);

    @Query("SELECT AVG(uj.rating) FROM USUARIO_JOGO uj WHERE uj.usuarios.idUsuario = :idUsuario")
    Double calcularMediaRatingPorUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT COUNT(uj) FROM USUARIO_JOGO uj WHERE uj.usuarios.idUsuario = :idUsuario AND uj.rating IS NOT NULL")
    Long contarAvaliacoesPorUsuario(@Param("idUsuario") Integer idUsuario);

}
