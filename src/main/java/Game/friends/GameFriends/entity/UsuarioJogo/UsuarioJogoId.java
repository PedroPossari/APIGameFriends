package Game.friends.GameFriends.entity.UsuarioJogo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioJogoId implements Serializable {

    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "ID_JOGO")
    private Integer idJogo;
}
