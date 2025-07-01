package Game.friends.GameFriends.entity.jogo;
import Game.friends.GameFriends.entity.JogoEntity;
import Game.friends.GameFriends.entity.UsuarioEntity;
import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoEntity;
import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioJogoEntityTest {

    @Test
    void testAllArgsConstructor() {
        UsuarioJogoId id = new UsuarioJogoId(1, 2);
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail("exemplo@email.com");

        JogoEntity jogo = new JogoEntity();
        jogo.setTitulo("Halo");

        UsuarioJogoEntity entity = new UsuarioJogoEntity(id, usuario, jogo, 8.5, false);

        assertEquals(id, entity.getId());
        assertEquals(usuario, entity.getUsuarios());
        assertEquals(jogo, entity.getJogos());
        assertEquals(8.5, entity.getRating());
        assertFalse(entity.getFavorito());
    }
}
