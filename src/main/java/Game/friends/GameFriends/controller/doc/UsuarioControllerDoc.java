package Game.friends.GameFriends.controller.doc;

import Game.friends.GameFriends.dto.Usuario.UsuarioDTO;
import Game.friends.GameFriends.dto.Usuario.UsuarioSearchDTO;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Usuário", description = "Endpoints para retornos e gerenciamento de usuários")
@Validated
public interface UsuarioControllerDoc {
    @Operation(summary = "Tornar um usuário administrador", description = "Troca role do usuário para administrador")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna usuário atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/admin/{id}")
    public ResponseEntity<UsuarioDTO> turnAdmin(@PathVariable("id")Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Retorna usuários pelo nome", description = "Usado para procurar um usuário pelo nome")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<UsuarioSearchDTO>> findAllContainsName(@RequestParam(required = false) String search,
                                                                      Integer page,
                                                                      Integer size);

    @Operation(summary = "Retorna usuários pelo ID", description = "Usado para procurar um usuário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/search/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Integer id) throws RegraDeNegocioException;
}
