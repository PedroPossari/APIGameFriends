package Game.friends.GameFriends.dto.usuario;

import Game.friends.GameFriends.dto.Usuario.UsuarioUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioUpdateDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testLoginValido() {
        UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
        dto.setLogin("novoLogin123");

        Set<ConstraintViolation<UsuarioUpdateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testLoginEmBranco() {
        UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
        dto.setLogin("");

        Set<ConstraintViolation<UsuarioUpdateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("login") &&
                        v.getMessage().equals("Login é obrigatório")));
    }
}