package Game.friends.GameFriends.controller;

import Game.friends.GameFriends.controller.doc.UsuarioControllerDoc;
import Game.friends.GameFriends.dto.Usuario.UsuarioDTO;
import Game.friends.GameFriends.dto.Usuario.UsuarioSearchDTO;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import Game.friends.GameFriends.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController implements UsuarioControllerDoc {
    private final UsuarioService usuarioService;

    @PutMapping("/admin/{id}")
    public ResponseEntity<UsuarioDTO> turnAdmin(@PathVariable("id")Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.turnAdmin(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UsuarioSearchDTO>> findAllContainsName(@RequestParam(required = false) String search,
                                                                      Integer page,
                                                                      Integer size) {
        return new ResponseEntity<>(usuarioService.getByName(search, page, size), HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }
}
