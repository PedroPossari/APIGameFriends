package Game.friends.GameFriends.dto.usuario;

import Game.friends.GameFriends.dto.Usuario.LoginDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidLoginDTO() {
        LoginDTO dto = new LoginDTO();
        dto.setLogin("usuario123");
        dto.setSenha("senhaSegura123");

        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testLoginBlank() {
        LoginDTO dto = new LoginDTO();
        dto.setLogin("");
        dto.setSenha("senhaSegura123");

        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("login") &&
                        v.getMessage().equals("Login é obrigatório")));
    }

    @Test
    void testSenhaBlank() {
        LoginDTO dto = new LoginDTO();
        dto.setLogin("usuario123");
        dto.setSenha(""); // inválido

        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("senha") &&
                        v.getMessage().equals("Senha é obrigatória")));
    }

    @Test
    void testBothFieldsBlank() {
        LoginDTO dto = new LoginDTO();
        dto.setLogin("");
        dto.setSenha("");

        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(dto);
        assertEquals(2, violations.size());
    }
}