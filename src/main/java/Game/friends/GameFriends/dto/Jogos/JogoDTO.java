package Game.friends.GameFriends.dto.Jogos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoDTO {
    @Schema(description = "ID do Jogo", example = "1")
    private Integer idJogo;

    @Schema(description = "Titulo do Jogo", example = "Persona 3")
    private String titulo;

    @Schema(description = "Data de lançamento do Jogo", example = "2006")
    private Integer anoLancamento;

    @Schema(description = "Média de Notas do Jogo", example = "8,3")
    private Double avgRating;

    @Schema(description = "Total de Notas do Jogo", example = "2344")
    private Integer totalRating;

    @Schema(description = "Generos do Jogo", example = "[\"Action RPG\"]")
    private String[] genero;

    @Schema(description = "Plataformas do Jogo", example = "[\"Playstation 5\"]")
    private String[] plataformas;

    @Schema(description = "Produtora do Jogo", example = "Atlus")
    private String produtora;

    @Schema(description = "Descrição do jogo", example = "Persona 3 é um JRPG lançado em...")
    private String descricao;

    @Schema(description = "Imagem do jogo", example = "https://imgur.com/upload")
    private String img;
}
