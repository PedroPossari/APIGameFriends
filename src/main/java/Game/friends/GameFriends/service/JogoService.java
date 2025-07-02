package Game.friends.GameFriends.service;

import Game.friends.GameFriends.dto.Jogos.*;
import Game.friends.GameFriends.dto.Usuario.UsuarioDTO;
import Game.friends.GameFriends.entity.JogoEntity;
import Game.friends.GameFriends.entity.UsuarioEntity;
import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoEntity;
import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoId;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import Game.friends.GameFriends.repository.JogoRepository;
import Game.friends.GameFriends.repository.UsuarioJogoRepository;
import Game.friends.GameFriends.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JogoService {
    private final JogoRepository jogoRepository;
    private final UsuarioJogoRepository usuarioJogoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;


    public List<JogoDTO> findAll(Integer page, Integer size, String filter, String search) {
        Page<JogoEntity> entities;
        Pageable pageable;

        if (filter != null &&
                (
                        filter.equalsIgnoreCase("anoLancamento") ||
                                filter.equalsIgnoreCase("titulo") ||
                                filter.equalsIgnoreCase("avgRating")
                )
        ){
            Sort sorting = Sort.by(filter);
            pageable = PageRequest.of(page,size, sorting);
        } else {
            pageable = PageRequest.of(page,size);
        }

        if (search != null && !search.isBlank()) entities = jogoRepository.findByTituloContainingIgnoreCase(search, pageable);
        else entities = jogoRepository.findAll(pageable);

        return entities.stream().map(entity -> objectMapper.convertValue(entity, JogoDTO.class)).collect(Collectors.toList());
    }

    public JogoDTO findById(Integer idJogo) {
        return objectMapper.convertValue(jogoRepository.findById(idJogo), JogoDTO.class);
    }


    public JogoDTO create(JogoCreateDTO jogoCreateDTO) {
        JogoEntity entity = objectMapper.convertValue(jogoCreateDTO, JogoEntity.class);

        jogoRepository.save(entity);

        return objectMapper.convertValue(entity, JogoDTO.class);
    }


    public JogoDTO update(Integer idJogo, JogoCreateDTO jogoUpdateDTO) throws RegraDeNegocioException {
        JogoEntity dbEntity = jogoRepository.findById(idJogo).orElseThrow(() -> new RegraDeNegocioException("Jogo não encontrado."));

        dbEntity.setTitulo(jogoUpdateDTO.getTitulo());
        dbEntity.setAnoLancamento(jogoUpdateDTO.getAnoLancamento());
        dbEntity.setGenero(jogoUpdateDTO.getGenero());
        dbEntity.setPlataformas(jogoUpdateDTO.getPlataformas());
        dbEntity.setProdutora(jogoUpdateDTO.getProdutora());
        dbEntity.setImg(jogoUpdateDTO.getImg());

        jogoRepository.save(dbEntity);

        return objectMapper.convertValue(dbEntity, JogoDTO.class);
    }


    public void delete(Integer idJogo) throws RegraDeNegocioException {
        jogoRepository.delete(jogoRepository.findById(idJogo).orElseThrow(() -> new RegraDeNegocioException("Jogo não encontrado")));
    }

    public ReviewDTO createReview(ReviewCreateDTO reviewCreateDTO) throws RegraDeNegocioException {
        UsuarioDTO loggedUser = usuarioService.getLoggedUser();
        UsuarioEntity userEntity = usuarioRepository.findById(loggedUser.getIdUsuario())
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));

        JogoEntity jogoEntity = jogoRepository.findById(reviewCreateDTO.getIdJogo())
                .orElseThrow(() -> new RegraDeNegocioException("Jogo não encontrado."));

        Optional<UsuarioJogoEntity> OEntity = usuarioJogoRepository.findByUsuarios_IdUsuarioAndJogos_IdJogo(loggedUser.getIdUsuario(), reviewCreateDTO.getIdJogo());

        if (OEntity.isPresent()) throw new RegraDeNegocioException("Você já avaliou esse jogo");

        UsuarioJogoEntity entity = new UsuarioJogoEntity();
        entity.setJogos(jogoEntity);
        entity.setUsuarios(userEntity);
        entity.setId(new UsuarioJogoId(userEntity.getIdUsuario(), jogoEntity.getIdJogo()));
        entity.setRating(reviewCreateDTO.getRating());

        if (jogoEntity.getTotalRating() == null) jogoEntity.setTotalRating(1);
        else jogoEntity.setTotalRating(jogoEntity.getTotalRating() + 1);

        if (jogoEntity.getAvgRating() == null) jogoEntity.setAvgRating(0.0);
        Double rates = (jogoEntity.getAvgRating() * jogoEntity.getTotalRating());

        jogoEntity.setAvgRating((rates + entity.getRating() )/ jogoEntity.getTotalRating());

        usuarioJogoRepository.save(entity);
        jogoRepository.save(jogoEntity);

        ReviewDTO dto = objectMapper.convertValue(reviewCreateDTO, ReviewDTO.class);
        dto.setIdUsuario(userEntity.getIdUsuario());

        return dto;
    }

    public ReviewDTO updateReview(ReviewCreateDTO reviewUpdateDTO) throws RegraDeNegocioException {
        UsuarioDTO loggedUser = usuarioService.getLoggedUser();
        UsuarioEntity userEntity = usuarioRepository.findById(loggedUser.getIdUsuario())
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));

        JogoEntity jogoEntity = jogoRepository.findById(reviewUpdateDTO.getIdJogo())
                .orElseThrow(() -> new RegraDeNegocioException("Jogo não encontrado."));

        UsuarioJogoEntity entity = usuarioJogoRepository.findByUsuarios_IdUsuarioAndJogos_IdJogo(loggedUser.getIdUsuario(), reviewUpdateDTO.getIdJogo())
                .orElseThrow(() -> new RegraDeNegocioException("Avaliação não encontrada"));


        Double rates = (jogoEntity.getAvgRating() * jogoEntity.getTotalRating());
        rates -= entity.getRating();
        entity.setRating(reviewUpdateDTO.getRating());
        rates += entity.getRating();

        jogoEntity.setAvgRating(rates / jogoEntity.getTotalRating());

        usuarioJogoRepository.save(entity);

        ReviewDTO dto = objectMapper.convertValue(reviewUpdateDTO, ReviewDTO.class);
        dto.setIdUsuario(userEntity.getIdUsuario());

        return dto;
    }

    public FavoriteDTO favoritar(FavoriteDTO favoriteDTO) throws RegraDeNegocioException {
        UsuarioDTO loggedUser = usuarioService.getLoggedUser();

        JogoEntity jogoEntity = jogoRepository.findById(favoriteDTO.getIdJogo())
                .orElseThrow(() -> new RegraDeNegocioException("Jogo não encontrado."));

        UsuarioJogoEntity entity = usuarioJogoRepository.findByUsuarios_IdUsuarioAndJogos_IdJogo(loggedUser.getIdUsuario(), favoriteDTO.getIdJogo())
                .orElseThrow(() -> new RegraDeNegocioException("Avaliação não encontrada"));

        entity.setFavorito(favoriteDTO.getFavorito());

        usuarioJogoRepository.save(entity);

        return favoriteDTO;
    }

    public List<JogoDTO> findFavoritos() throws RegraDeNegocioException {
        UsuarioDTO loggedUser = usuarioService.getLoggedUser();

        List<UsuarioJogoEntity> entities = usuarioJogoRepository.findByUsuarios_IdUsuario(loggedUser.getIdUsuario());

        if (entities.isEmpty()) throw new RegraDeNegocioException("Usuário não possui jogos favoritos.");

        List<JogoDTO> favoritos = entities.stream().filter(UsuarioJogoEntity::getFavorito).map(UsuarioJogoEntity::getJogos)
                .map(jogo -> objectMapper.convertValue(jogo, JogoDTO.class)).toList();

        return favoritos;
    }

    public List<ReviewDTO> findReviewsByUsuario() throws RegraDeNegocioException {
        UsuarioDTO loggedUser = usuarioService.getLoggedUser();

        List<UsuarioJogoEntity> lista = usuarioJogoRepository.findByUsuarios_IdUsuario(loggedUser.getIdUsuario());

        return lista.stream().filter(u -> u.getRating() != null).map(u -> {
            ReviewDTO dto = new ReviewDTO();
            dto.setIdUsuario(loggedUser.getIdUsuario());
            dto.setIdJogo(u.getJogos().getIdJogo());
            dto.setRating(u.getRating());
            return dto;
        }).collect(Collectors.toList());
    }

}
