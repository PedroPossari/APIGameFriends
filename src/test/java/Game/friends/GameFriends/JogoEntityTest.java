package Game.friends.GameFriends;

import Game.friends.GameFriends.entity.JogoEntity;
import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoEntity;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JogoEntityTest {

    @Test
    void testGettersAndSetters() {
        JogoEntity jogo = new JogoEntity();

        jogo.setIdJogo(10);
        jogo.setTitulo("The Witcher 3");
        jogo.setAnoLancamento(2015);
        jogo.setAvgRating(9.5);
        jogo.setTotalRating(10000);
        jogo.setGenero(new String[]{"RPG", "Ação"});
        jogo.setPlataformas(new String[]{"PC", "PS4", "Xbox"});
        jogo.setProdutora("CD Projekt");
        jogo.setImg("link-da-imagem");
        jogo.setDescricao("Jogo de ação e RPG em mundo aberto.");

        assertEquals(10, jogo.getIdJogo());
        assertEquals("The Witcher 3", jogo.getTitulo());
        assertEquals(2015, jogo.getAnoLancamento());
        assertEquals(9.5, jogo.getAvgRating());
        assertEquals(10000, jogo.getTotalRating());
        assertArrayEquals(new String[]{"RPG", "Ação"}, jogo.getGenero());
        assertArrayEquals(new String[]{"PC", "PS4", "Xbox"}, jogo.getPlataformas());
        assertEquals("CD Projekt", jogo.getProdutora());
        assertEquals("link-da-imagem", jogo.getImg());
        assertEquals("Jogo de ação e RPG em mundo aberto.", jogo.getDescricao());
    }

    @Test
    void testConstrutorAllArgs() {
        String[] generos = {"FPS"};
        String[] plataformas = {"PC"};
        Set<UsuarioJogoEntity> usuarios = new HashSet<>();

        JogoEntity jogo = new JogoEntity(
                1,
                "Counter-Strike",
                2000,
                8.0,
                5000,
                generos,
                plataformas,
                "Valve",
                "img.jpg",
                "Jogo de tiro em primeira pessoa.",
                usuarios
        );

        assertEquals(1, jogo.getIdJogo());
        assertEquals("Counter-Strike", jogo.getTitulo());
        assertEquals(2000, jogo.getAnoLancamento());
        assertEquals(8.0, jogo.getAvgRating());
        assertEquals(5000, jogo.getTotalRating());
        assertArrayEquals(generos, jogo.getGenero());
        assertArrayEquals(plataformas, jogo.getPlataformas());
        assertEquals("Valve", jogo.getProdutora());
        assertEquals("img.jpg", jogo.getImg());
        assertEquals("Jogo de tiro em primeira pessoa.", jogo.getDescricao());
        assertEquals(usuarios, jogo.getUsuarioJogos());
    }

    @Test
    void testUsuarioJogosSet() {
        JogoEntity jogo = new JogoEntity();
        UsuarioJogoEntity usuarioJogo = new UsuarioJogoEntity();
        Set<UsuarioJogoEntity> usuarioJogos = new HashSet<>();
        usuarioJogos.add(usuarioJogo);

        jogo.setUsuarioJogos(usuarioJogos);

        assertNotNull(jogo.getUsuarioJogos());
        assertEquals(1, jogo.getUsuarioJogos().size());
        assertTrue(jogo.getUsuarioJogos().contains(usuarioJogo));
    }
}