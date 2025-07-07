package Game.friends.GameFriends.dto.Jogos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO extends ReviewCreateDTO{
    private Integer idUsuario;
}
