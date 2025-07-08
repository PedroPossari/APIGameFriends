package Game.friends.GameFriends.dto.Jogos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCheckDTO {
    @Schema(description = "Retorna se foi avaliado ou não", example = "true")
    private Boolean isReviewed;
}
