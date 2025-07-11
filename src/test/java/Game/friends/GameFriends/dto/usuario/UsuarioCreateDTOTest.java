package Game.friends.GameFriends.dto.usuario;

import Game.friends.GameFriends.dto.Usuario.UsuarioCreateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioCreateDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCamposValidos() {
        UsuarioCreateDTO dto = new UsuarioCreateDTO();
        dto.setLogin("pedro123");
        dto.setEmail("pedro@email.com");
        dto.setSenha("123456");


        Set<ConstraintViolation<UsuarioCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testLoginEmBranco() {
        UsuarioCreateDTO dto = new UsuarioCreateDTO();
        dto.setLogin("");
        dto.setEmail("pedro@email.com");
        dto.setSenha("123456");


        Set<ConstraintViolation<UsuarioCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("login") &&
                        v.getMessage().equals("Login é obrigatório")));
    }

    @Test
    void testEmailEmBranco() {
        UsuarioCreateDTO dto = new UsuarioCreateDTO();
        dto.setLogin("pedro123");
        dto.setEmail("");
        dto.setSenha("123456");


        Set<ConstraintViolation<UsuarioCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("email") &&
                        v.getMessage().equals("Email é obrigatório")));
    }

    @Test
    void testSenhaEmBranco() {
        UsuarioCreateDTO dto = new UsuarioCreateDTO();
        dto.setLogin("pedro123");
        dto.setEmail("pedro@email.com");
        dto.setSenha("");


        Set<ConstraintViolation<UsuarioCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("senha") &&
                        v.getMessage().equals("Senha é obrigatória")));
    }

    @Test
    void testNomeCargoEmBranco() {
        UsuarioCreateDTO dto = new UsuarioCreateDTO();
        dto.setLogin("pedro123");
        dto.setEmail("pedro@email.com");
        dto.setSenha("123456");


        Set<ConstraintViolation<UsuarioCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("nomeCargo") &&
                        v.getMessage().equals("Nome do cargo é obrigatório")));
    }

    @Test
    void testTodosCamposEmBranco() {
        UsuarioCreateDTO dto = new UsuarioCreateDTO();
        dto.setLogin("");
        dto.setEmail("");
        dto.setSenha("");


        Set<ConstraintViolation<UsuarioCreateDTO>> violations = validator.validate(dto);
        assertEquals(4, violations.size());
    }
}