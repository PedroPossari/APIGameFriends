package Game.friends.GameFriends.controller;


import Game.friends.GameFriends.controller.doc.AuthControllerDoc;
import Game.friends.GameFriends.dto.Google.GoogleLoginDTO;
import Game.friends.GameFriends.dto.Usuario.*;
import Game.friends.GameFriends.entity.UsuarioEntity;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import Game.friends.GameFriends.security.GoogleTokenVerifier;
import Game.friends.GameFriends.security.TokenService;
import Game.friends.GameFriends.service.UsuarioService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Slf4j
@RestController
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDoc
{
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    public final AuthenticationManager authenticationManager;
    private final GoogleTokenVerifier googleTokenVerifier;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    @PostMapping
    public String auth(@RequestBody @Valid LoginDTO loginDTO)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getLogin(),
                        loginDTO.getSenha()
                );

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UsuarioEntity usuarioValidado = (UsuarioEntity) authentication.getPrincipal();

        return tokenService.generateToken(usuarioValidado);
    }

    @GetMapping("/Usuario-logado")
    public UsuarioDTO getLoggedUser() throws RegraDeNegocioException
    {
        return usuarioService.getLoggedUser();
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException, MessagingException, IOException {
        return ResponseEntity.ok(usuarioService.create(usuarioCreateDTO));
    }

    @PutMapping("/senha")
    public ResponseEntity<String> changePassword(@RequestBody @Valid UsuarioSenhaDTO usuarioSenhaDTO) throws RegraDeNegocioException
    {
        usuarioService.changePassword(usuarioSenhaDTO);
        return ResponseEntity.ok("Senha alterada com sucesso!");
    }

    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioDTO> update(@RequestBody @Valid UsuarioUpdateDTO dto) throws RegraDeNegocioException
    {
        return ResponseEntity.ok(usuarioService.update(dto));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> delete() throws RegraDeNegocioException
    {
        usuarioService.desativarConta();
        return ResponseEntity.ok("Usuário desativado.");
    }

    @PostMapping("/google")
    public ResponseEntity<String> googleLogin(@RequestBody GoogleLoginDTO googleLoginDTO) {
        try {
            GoogleIdToken.Payload payload = googleTokenVerifier.verify(googleLoginDTO.getIdToken());

            if (payload == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID Token inválido.");
            }

            UsuarioEntity usuario = usuarioService.autenticarOuCadastrarUsuarioGoogle(payload);
            String token = tokenService.generateToken(usuario);

            return ResponseEntity.ok(token);

        } catch (Exception e) {
            logger.error("Erro no login com Google", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no login com Google");
        }
    }

}
