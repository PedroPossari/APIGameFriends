package Game.friends.GameFriends.service;

import Game.friends.GameFriends.dto.Jogos.JogoDTO;
import Game.friends.GameFriends.repository.JogoRepository;
import Game.friends.GameFriends.repository.UsuarioJogoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JogoService {
    private final JogoRepository jogoRepository;
    private final UsuarioJogoRepository usuarioJogoRepository;
    private final ObjectMapper objectMapper;

    public List<JogoDTO> findAll(Integer page, Integer size, String filter) {


        return jogoRepository.findAll().stream().map(jogo -> objectMapper.convertValue(jogo, JogoDTO.class)).collect(Collectors.toList());
    }

    public JogoDTO findById() {
        return null;
    }


    public JogoDTO create() {
        return null;
    }


    public JogoDTO update() {
        return null;
    }


    public void delete() {

    }
}
