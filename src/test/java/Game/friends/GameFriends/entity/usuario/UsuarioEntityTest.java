package Game.friends.GameFriends.entity.usuario;

import Game.friends.GameFriends.entity.CargoEntity;
import Game.friends.GameFriends.entity.UsuarioEntity;
import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoEntity;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioEntityTest {

    @Test
    void testGettersAndSetters() {
        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setIdUsuario(1);
        usuario.setLogin("pedro123");
        usuario.setEmail("pedro@email.com");
        usuario.setSenha("senhaSegura");

        assertEquals(1, usuario.getIdUsuario());
        assertEquals("pedro123", usuario.getLogin());
        assertEquals("pedro@email.com", usuario.getEmail());
        assertEquals("senhaSegura", usuario.getSenha());
    }

    @Test
    void testGetAuthorities() {
        UsuarioEntity usuario = new UsuarioEntity();

        CargoEntity cargoMock1 = mock(CargoEntity.class);
        CargoEntity cargoMock2 = mock(CargoEntity.class);

        Set<CargoEntity> cargos = new HashSet<>();
        cargos.add(cargoMock1);
        cargos.add(cargoMock2);

        usuario.setCargos(cargos);

        assertEquals(cargos, usuario.getAuthorities());
    }

    @Test
    void testUsuarioJogos() {
        UsuarioEntity usuario = new UsuarioEntity();

        UsuarioJogoEntity jogo1 = new UsuarioJogoEntity();
        UsuarioJogoEntity jogo2 = new UsuarioJogoEntity();

        Set<UsuarioJogoEntity> jogos = new HashSet<>();
        jogos.add(jogo1);
        jogos.add(jogo2);

        usuario.setUsuarioJogos(jogos);

        assertEquals(2, usuario.getUsuarioJogos().size());
        assertTrue(usuario.getUsuarioJogos().contains(jogo1));
    }

    @Test
    void testUserDetailsImplementation() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setLogin("usuario123");
        usuario.setSenha("senha123");

        assertEquals("usuario123", usuario.getUsername());
        assertEquals("senha123", usuario.getPassword());

        assertTrue(usuario.isAccountNonExpired());
        assertTrue(usuario.isAccountNonLocked());
        assertTrue(usuario.isCredentialsNonExpired());
        assertTrue(usuario.isEnabled());
    }
}