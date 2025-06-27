package Game.friends.GameFriends.controller.doc;

import Game.friends.GameFriends.dto.Jogos.JogoCreateDTO;
import Game.friends.GameFriends.dto.Jogos.JogoDTO;
import Game.friends.GameFriends.dto.Jogos.ReviewCreateDTO;
import Game.friends.GameFriends.dto.Jogos.ReviewDTO;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface JogoControllerDoc {
    @Operation(summary = "Listar Jogos", description = "Lista todos os Jogos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de jogos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping()
    public ResponseEntity<List<JogoDTO>> findAll(Integer page, Integer size, @RequestParam(required = false) String filter);

    @Operation(summary = "Jogo por ID", description = "Lista um jogo baseado no ID fornecido")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o Jogo"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idJogo}")
    public ResponseEntity<JogoDTO> find(@PathVariable("idJogo") Integer idJogo);

    @Operation(summary = "Adicionar Jogo", description = "Adiciona um jogo")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Retorna jogo adicionado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping()
    public ResponseEntity<JogoDTO> create(@Valid @RequestBody JogoCreateDTO jogoCreateDTO);

    @Operation(summary = "Editar Jogo", description = "Edita um jogo")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna jogo editado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idJogo}")
    public ResponseEntity<JogoDTO> update(@PathVariable("idJogo") Integer idJogo, @Valid @RequestBody JogoCreateDTO jogoCreateDTO)
        throws RegraDeNegocioException;

    @Operation(summary = "Deleta Jogo", description = "Deleta um jogo")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Jogo deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idJogo}")
    public ResponseEntity<Void> delete(@PathVariable("idJogo") Integer idJogo) throws RegraDeNegocioException ;

    @Operation(summary = "Adicionar uma review", description = "Adiciona review de jogo")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Retorna os dados da review"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/review")
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewCreateDTO reviewCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Editar uma review", description = "Edita review de um jogo")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados da review editada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/review")
    public ResponseEntity<ReviewDTO> updateReview(@Valid @RequestBody ReviewCreateDTO reviewUpdateDTO) throws RegraDeNegocioException;
}
