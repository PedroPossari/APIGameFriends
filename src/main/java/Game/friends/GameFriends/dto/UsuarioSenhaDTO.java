package Game.friends.GameFriends.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsuarioSenhaDTO {

    @Schema(example = "senhaAntiga123", description = "Senha atual do usuário")
    @NotBlank(message = "Senha atual é obrigatória")
    private String senhaAtual;

    @Schema(example = "novaSenha456", description = "Nova senha desejada")
    @NotBlank(message = "Nova senha é obrigatória")
    private String novaSenha;
}
