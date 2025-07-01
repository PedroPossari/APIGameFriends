package Game.friends.GameFriends.service;

import Game.friends.GameFriends.dto.Usuario.UsuarioCreateDTO;
import Game.friends.GameFriends.dto.Usuario.UsuarioDTO;
import Game.friends.GameFriends.dto.Usuario.UsuarioSenhaDTO;
import Game.friends.GameFriends.dto.Usuario.UsuarioUpdateDTO;
import Game.friends.GameFriends.entity.CargoEntity;
import Game.friends.GameFriends.entity.UsuarioEntity;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import Game.friends.GameFriends.repository.CargoRepository;
import Game.friends.GameFriends.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public Optional<UsuarioEntity> findbyLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public UsuarioDTO getLoggedUser() throws RegraDeNegocioException {
        UsuarioEntity usuarioLogado = findByID(getIdLoggedUser());
        return retornarDTO(usuarioLogado);
    }

    private Integer getIdLoggedUser() {
        String findUserId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Integer.parseInt(findUserId);
    }

    private UsuarioEntity findByID(Integer idUsuario) throws RegraDeNegocioException {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));
    }

    private UsuarioDTO retornarDTO(UsuarioEntity usuario) {
        UsuarioDTO dto = objectMapper.convertValue(usuario, UsuarioDTO.class);

        Set<String> roles = usuario.getCargos()
                .stream()
                .map(cargo -> cargo.getNome())
                .collect(Collectors.toSet());

        dto.setRoles(roles);
        return dto;
    }

    public UsuarioDTO create(UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException, IOException, MessagingException {
        if (usuarioRepository.findByEmail(usuarioCreateDTO.getEmail()).isPresent()) {
            throw new RegraDeNegocioException("Email já está em uso");
        }

        UsuarioEntity entity = new UsuarioEntity();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entity.setLogin(usuarioCreateDTO.getLogin());
        entity.setSenha(encoder.encode(usuarioCreateDTO.getSenha()));
        entity.setEmail(usuarioCreateDTO.getEmail());

        CargoEntity cargo = cargoRepository.findByNome(usuarioCreateDTO.getNomeCargo())
                .orElseThrow(() -> new RegraDeNegocioException("Cargo não encontrado"));

        entity.setCargos(Set.of(cargo));

        String newEmail = usuarioCreateDTO.getEmail();
        String html = emailService.carregarTemplateHtml();
        emailService.sendHtmlEmail(newEmail, "Conta Google cadastrada com sucesso!", html);

        usuarioRepository.save(entity);
        return retornarDTO(entity);
    }

    public void changePassword(UsuarioSenhaDTO dto) throws RegraDeNegocioException {
        UsuarioEntity usuario = findByID(getIdLoggedUser());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            throw new RegraDeNegocioException("Senha atual incorreta");
        }

        usuario.setSenha(encoder.encode(dto.getNovaSenha()));
        usuarioRepository.save(usuario);
    }

    public UsuarioDTO update(UsuarioUpdateDTO dto) throws RegraDeNegocioException {
        UsuarioEntity usuario = findByID(getIdLoggedUser());

        usuario.setLogin(dto.getLogin());
        UsuarioEntity updated = usuarioRepository.save(usuario);

        return retornarDTO(updated);
    }

    public void desativarConta() throws RegraDeNegocioException {
        UsuarioEntity usuario = findByID(getIdLoggedUser());
        usuarioRepository.delete(usuario);
    }

    public Optional<UsuarioEntity> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioEntity autenticarOuCadastrarUsuarioGoogle(GoogleIdToken.Payload payload) throws RegraDeNegocioException, IOException, MessagingException {
        String email = payload.getEmail();
        String nome = (String) payload.get("name");

        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            return optionalUsuario.get();
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(email);
        usuario.setLogin(email);
        usuario.setSenha("");

        CargoEntity cargo = cargoRepository.findByNomeIgnoreCase("ROLE_USUARIO")
                .orElseThrow(() -> new RegraDeNegocioException("Cargo 'ROLE_USUARIO' não encontrado no sistema."));

        usuario.setCargos(Set.of(cargo));

        String newEmail = payload.getEmail();
        String html = emailService.carregarTemplateHtml();
        emailService.sendHtmlEmail(newEmail, "Conta Google cadastrada com sucesso!", html);

        return usuarioRepository.save(usuario);
    }


}
