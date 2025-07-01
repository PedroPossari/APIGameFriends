package Game.friends.GameFriends.dto.usuario;

import Game.friends.GameFriends.dto.Usuario.UsuarioSenhaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioSenhaDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCamposValidos() {
        UsuarioSenhaDTO dto = new UsuarioSenhaDTO();
        dto.setSenhaAtual("senhaAntiga123");
        dto.setNovaSenha("novaSenha456");

        Set<ConstraintViolation<UsuarioSenhaDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testSenhaAtualEmBranco() {
        UsuarioSenhaDTO dto = new UsuarioSenhaDTO();
        dto.setSenhaAtual("");
        dto.setNovaSenha("novaSenha456");

        Set<ConstraintViolation<UsuarioSenhaDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("senhaAtual") &&
                        v.getMessage().equals("Senha atual é obrigatória")));
    }

    @Test
    void testNovaSenhaEmBranco() {
        UsuarioSenhaDTO dto = new UsuarioSenhaDTO();
        dto.setSenhaAtual("senhaAntiga123");
        dto.setNovaSenha("");

        Set<ConstraintViolation<UsuarioSenhaDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("novaSenha") &&
                        v.getMessage().equals("Nova senha é obrigatória")));
    }

    @Test
    void testAmbasSenhasEmBranco() {
        UsuarioSenhaDTO dto = new UsuarioSenhaDTO();
        dto.setSenhaAtual("");
        dto.setNovaSenha("");

        Set<ConstraintViolation<UsuarioSenhaDTO>> violations = validator.validate(dto);
        assertEquals(2, violations.size());
    }
}