package Game.friends.GameFriends;

import Game.friends.GameFriends.entity.CargoEntity;
import Game.friends.GameFriends.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CargoEntityTest {

    @Test
    void testGetAndSetIdCargo() {
        CargoEntity cargo = new CargoEntity();
        cargo.setIdCargo(1);
        assertEquals(1, cargo.getIdCargo());
    }

    @Test
    void testGetAndSetNome() {
        CargoEntity cargo = new CargoEntity();
        cargo.setNome("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", cargo.getNome());
    }

    @Test
    void testGetAndSetUsuarios() {
        CargoEntity cargo = new CargoEntity();
        Set<UsuarioEntity> usuarios = new HashSet<>();
        UsuarioEntity user = new UsuarioEntity();
        usuarios.add(user);
        cargo.setUsuarios(usuarios);
        assertEquals(1, cargo.getUsuarios().size());
        assertTrue(cargo.getUsuarios().contains(user));
    }

    @Test
    void testGetAuthority() {
        CargoEntity cargo = new CargoEntity();
        cargo.setNome("ROLE_USER");
        assertEquals("ROLE_USER", cargo.getAuthority());
    }

    @Test
    void testUsuariosInicialmenteNulo() {
        CargoEntity cargo = new CargoEntity();
        assertNull(cargo.getUsuarios());
    }

    @Test
    void testGetAuthorityComNomeNulo() {
        CargoEntity cargo = new CargoEntity();
        cargo.setNome(null);
        assertNull(cargo.getAuthority());
    }
}