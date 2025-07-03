package Game.friends.GameFriends.controller;

import Game.friends.GameFriends.controller.doc.JogoControllerDoc;
import Game.friends.GameFriends.dto.Jogos.*;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import Game.friends.GameFriends.service.JogoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@Tag(name = "Jogos", description = "Operações de Jogos")
@RequestMapping("/jogos")
public class JogoController implements JogoControllerDoc {
    private final JogoService jogoService;

    @GetMapping()
    public ResponseEntity<List<JogoDTO>> findAll(Integer page, Integer size,
                                                 @RequestParam(required = false) String filter,
                                                 @RequestParam(required = false) String search) {
        return new ResponseEntity<>(jogoService.findAll(page,size,filter,search), HttpStatus.OK);
    }

    @GetMapping("/{idJogo}")
    public ResponseEntity<JogoDTO> find(@PathVariable("idJogo") Integer idJogo) {
        return new ResponseEntity<>(jogoService.findById(idJogo), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<JogoDTO> create(@Valid @RequestBody JogoCreateDTO jogoCreateDTO) {
        return new ResponseEntity<>(jogoService.create(jogoCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{idJogo}")
    public ResponseEntity<JogoDTO> update(@PathVariable("idJogo") Integer idJogo, @Valid @RequestBody JogoCreateDTO jogoUpdateDTO)
        throws RegraDeNegocioException
    {
        return new ResponseEntity<>(jogoService.update(idJogo, jogoUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idJogo}")
    public ResponseEntity<Void> delete(@PathVariable("idJogo") Integer idJogo) throws RegraDeNegocioException  {
        jogoService.delete(idJogo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewCreateDTO reviewCreateDTO)
            throws RegraDeNegocioException
    {
        return new ResponseEntity<>(jogoService.createReview(reviewCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/review")
    public ResponseEntity<ReviewDTO> updateReview(@Valid @RequestBody ReviewCreateDTO reviewUpdateDTO)
            throws RegraDeNegocioException
    {
        return new ResponseEntity<>(jogoService.updateReview(reviewUpdateDTO), HttpStatus.OK);
    }

    @PutMapping("/favoritos")
    public ResponseEntity<FavoriteDTO> favoritar(@Valid @RequestBody FavoriteDTO favoriteDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(jogoService.favoritar(favoriteDTO), HttpStatus.OK);
    }

    @GetMapping("/favoritos")
    public ResponseEntity<List<JogoDTO>> findFavoritos() throws RegraDeNegocioException {
        return new ResponseEntity<>(jogoService.findFavoritos(), HttpStatus.OK);
    }

    @GetMapping("/favoritos/{idJogo}")
    public ResponseEntity<FavoriteDTO> isFavorito(@PathVariable("idJogo")Integer idJogo) throws RegraDeNegocioException {
        return new ResponseEntity<>(jogoService.isFavorito(idJogo), HttpStatus.OK);
    }

    @GetMapping("/usuario/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewsByUsuario() throws RegraDeNegocioException {
        return new ResponseEntity<>(jogoService.findReviewsByUsuario(), HttpStatus.OK);
    }
}
