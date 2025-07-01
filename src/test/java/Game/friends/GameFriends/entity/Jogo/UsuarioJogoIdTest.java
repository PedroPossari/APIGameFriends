package Game.friends.GameFriends.entity.Jogo;

import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioJogoIdTest {

    @Test
    void testAllArgsConstructorAndEquality() {
        UsuarioJogoId id1 = new UsuarioJogoId(1, 100);
        UsuarioJogoId id2 = new UsuarioJogoId(1, 100);
        UsuarioJogoId id3 = new UsuarioJogoId(2, 200);

        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
    }

    @Test
    void testNoArgsConstructor() {
        UsuarioJogoId id = new UsuarioJogoId();
        assertNotNull(id);
    }

    @Test
    void testSetAndGetUsingReflection() throws Exception {
        UsuarioJogoId id = new UsuarioJogoId();

        var fieldUsuario = UsuarioJogoId.class.getDeclaredField("idUsuario");
        var fieldJogo = UsuarioJogoId.class.getDeclaredField("idJogo");

        fieldUsuario.setAccessible(true);
        fieldJogo.setAccessible(true);

        fieldUsuario.set(id, 10);
        fieldJogo.set(id, 50);

        assertEquals(10, fieldUsuario.get(id));
        assertEquals(50, fieldJogo.get(id));
    }
}