package Game.friends.GameFriends.dto.Jogos;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioComAvaliacaoDTO {
    private Integer idUsuario;
    private String login;
    private String email;
    private Set<String> roles;
    private Double mediaAvaliacoes;
    private Long quantidadeAvaliacoes;

}