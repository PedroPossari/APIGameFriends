package Game.friends.GameFriends.dto.Jogos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoCreateDTO {
    @Schema(description = "Titulo do Jogo", example = "Persona 3")
    @NotNull
    private String titulo;

    @Schema(description = "Data de lançamento do Jogo", example = "2006")
    @NotNull
    private Integer anoLancamento;

    @Schema(description = "Generos do Jogo", example = "Action RPG")
    @NotNull
    private String[] genero;

    @Schema(description = "Plataformas do Jogo", example = "Playstation 5")
    @NotNull
    private String[] plataformas;

    @Schema(description = "Produtora do Jogo", example = "Atlus")
    @NotNull
    private String produtora;

    @Schema(description = "Imagem do jogo", example = "https://imgur.com/upload")
    @NotNull
    private String img;

    @Schema(description = "Descrição do jogo", example = "Persona 3 é um JRPG lançado em...")
    @NotNull
    private String descricao;
}
