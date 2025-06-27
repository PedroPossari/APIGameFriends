package Game.friends.GameFriends.dto.Jogos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoDTO {
    private Integer idJogo;

    private String titulo;

    private Integer anoLancamento;

    private Double avgRating;

    private Integer totalRating;

    private String[] genero;

    private String[] plataformas;

    private String produtora;

    private String descricao;

    private String img;
}
