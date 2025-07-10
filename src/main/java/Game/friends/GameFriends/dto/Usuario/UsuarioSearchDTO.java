package Game.friends.GameFriends.dto.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSearchDTO {
    private Integer idUsuario;
    private String login;
}
