package Game.friends.GameFriends.dto.Jogos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoCreateDTO {
    private String titulo;

    private Integer anoLancamento;

    private String[] genero;

    private String[] plataformas;

    private String produtora;

    private String img;

    private String descricao;
}
