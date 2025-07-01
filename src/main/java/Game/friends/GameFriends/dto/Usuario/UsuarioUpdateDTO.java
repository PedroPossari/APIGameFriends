package Game.friends.GameFriends.dto.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsuarioUpdateDTO {

    @Schema(example = "novoLogin123", description = "Novo login do usuário")
    @NotBlank(message = "Login é obrigatório")
    private String login;
}
