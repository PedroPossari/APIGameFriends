package Game.friends.GameFriends.entity.UsuarioJogo;

import Game.friends.GameFriends.entity.JogoEntity;
import Game.friends.GameFriends.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "USUARIO_JOGO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioJogoEntity {

    @EmbeddedId
    private UsuarioJogoId id = new UsuarioJogoId();

    @ManyToOne
    @MapsId("ID_USUARIO")
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuarios;

    @ManyToOne
    @MapsId("ID_JOGO")
    @JoinColumn(name = "ID_JOGO")
    private JogoEntity jogos;

    private Double rating;
}
