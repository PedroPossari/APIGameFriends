package Game.friends.GameFriends.controller;


import Game.friends.GameFriends.controller.doc.AuthControllerDoc;
import Game.friends.GameFriends.dto.*;
import Game.friends.GameFriends.entity.UsuarioEntity;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import Game.friends.GameFriends.security.TokenService;
import Game.friends.GameFriends.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDoc
{
    public final AuthenticationManager authenticationManager;
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
    public ResponseEntity<UsuarioDTO> register(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException
    {
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

}
