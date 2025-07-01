package Game.friends.GameFriends.dto.usuario;

import Game.friends.GameFriends.dto.Usuario.UsuarioDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDTOTest {

    @Test
    void testGettersAndSetters() {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setIdUsuario(1);
        dto.setEmail("usuario@email.com");
        dto.setLogin("usuario123");

        assertEquals(1, dto.getIdUsuario());
        assertEquals("usuario@email.com", dto.getEmail());
        assertEquals("usuario123", dto.getLogin());
    }
}