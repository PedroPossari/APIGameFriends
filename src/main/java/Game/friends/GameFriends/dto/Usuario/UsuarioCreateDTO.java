package Game.friends.GameFriends.dto.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsuarioCreateDTO {

    @Schema(example = "pedro123", description = "Login do usuário")
    @NotBlank(message = "Login é obrigatório")
    private String login;

    @Schema(example = "pedro@email.com", description = "Email do usuário")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Schema(example = "123456", description = "Senha do usuário")
    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    @Schema(example = "ROLE_ADMIN", description = "Cargo do usuário (ex: ROLE_ADMIN ou ROLE_USUARIO)")
    @NotBlank(message = "Nome do cargo é obrigatório")
    private String nomeCargo;
}
