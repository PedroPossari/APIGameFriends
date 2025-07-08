package Game.friends.GameFriends.dto.Jogos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDTO {
    @Schema(description = "ID Do Jogo", example = "1")
    @NotNull
    private Integer idJogo;
    @Schema(description = "Se é favorito ou não", example = "true")
    @NotNull
    private Boolean favorito;
}
