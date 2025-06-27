package Game.friends.GameFriends.repository;

import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioJogoRepository extends JpaRepository<UsuarioJogoEntity, Integer> {
    Optional<UsuarioJogoEntity> findByUsuarios_IdUsuarioAndJogos_IdJogo(Integer idUsuario, Integer idJogo);

    List<UsuarioJogoEntity> findByJogos_IdJogo(Integer idJogo);

    List<UsuarioJogoEntity> findByUsuarios_IdUsuario(Integer idUsuario);
}
