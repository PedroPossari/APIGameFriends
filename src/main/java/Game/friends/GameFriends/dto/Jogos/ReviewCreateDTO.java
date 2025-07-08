package Game.friends.GameFriends.dto.Jogos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDTO {
    @Schema(description = "ID Do Jogo", example = "1")
    @NotNull
    private Integer idJogo;
    @Schema(description = "Nota Do Jogo", example = "8")
    @NotNull
    private Double rating;
}
