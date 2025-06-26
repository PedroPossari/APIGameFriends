package Game.friends.GameFriends.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@Tag(name = "Jogos", description = "Operações de Jogos")
@RequestMapping("/jogos")
public class JogoController {
}
