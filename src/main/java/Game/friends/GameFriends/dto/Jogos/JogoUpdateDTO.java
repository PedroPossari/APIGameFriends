package Game.friends.GameFriends.dto.Jogos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JogoUpdateDTO extends JogoCreateDTO{
    private Double avgRating;
    private Integer totalRating;
}
