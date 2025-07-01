package Game.friends.GameFriends.controller.doc;

import Game.friends.GameFriends.dto.*;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

@Tag(name = "Autenticação", description = "Endpoints para autenticação e gerenciamento de usuários")
@Validated
public interface AuthControllerDoc {

    @Operation(summary = "Autenticar usuário", description = "Autentica o usuário e retorna um token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Token JWT gerado com sucesso")
    })
    String auth(
            @Parameter(description = "Dados de login do usuário", required = true)
            @Valid @RequestBody LoginDTO loginDTO);

    @Operation(summary = "Obter dados do usuário logado", description = "Retorna os dados do usuário atualmente autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário logado retornado com sucesso")
    })
    UsuarioDTO getLoggedUser() throws RegraDeNegocioException;

    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso")
    })
    ResponseEntity<UsuarioDTO> register(
            @Parameter(description = "Dados para criação do usuário", required = true)
            @Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException, MessagingException, IOException;

    @Operation(summary = "Alterar senha do usuário logado", description = "Atualiza a senha do usuário atualmente autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso")
    })
    ResponseEntity<String> changePassword(
            @Parameter(description = "Dados para alteração de senha", required = true)
            @Valid @RequestBody UsuarioSenhaDTO usuarioSenhaDTO) throws RegraDeNegocioException;

    @Operation(summary = "Atualizar dados do usuário logado", description = "Atualiza os dados do usuário atualmente autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    })
    ResponseEntity<UsuarioDTO> update(
            @Parameter(description = "Dados para atualização do usuário", required = true)
            @Valid @RequestBody UsuarioUpdateDTO dto) throws RegraDeNegocioException;

    @Operation(summary = "Desativar usuário logado", description = "Remove o usuário atualmente autenticado do sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário desativado com sucesso")
    })
    ResponseEntity<String> delete() throws RegraDeNegocioException;
}
