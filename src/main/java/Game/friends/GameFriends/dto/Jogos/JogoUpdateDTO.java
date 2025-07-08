package Game.friends.GameFriends.dto.Jogos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JogoUpdateDTO extends JogoCreateDTO{
    @Schema(description = "Média Do Jogo", example = "6.32")
    @NotNull
    private Double avgRating;
    @Schema(description = "Total de Avaliações Do Jogo", example = "2334")
    @NotNull
    private Integer totalRating;
}
