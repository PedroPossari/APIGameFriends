package Game.friends.GameFriends.dto.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioDTO {

    @Schema(description = "ID do usuário", example = "1")
    private Integer idUsuario;

    private String email;

    @Schema(description = "Login do usuário", example = "usuario123")
    private String login;

    @Schema(description = "Cargos/roles do usuário")
    private Set<String> roles;
}
