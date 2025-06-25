package Game.friends.GameFriends.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioDTO {

    @Schema(description = "ID do usuário", example = "1")
    private Integer idUsuario;

    @Schema(description = "Login do usuário", example = "usuario123")
    private String login;
}
