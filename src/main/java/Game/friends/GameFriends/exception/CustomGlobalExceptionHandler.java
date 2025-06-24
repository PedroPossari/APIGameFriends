package Game.friends.GameFriends.exception;

import Game.friends.GameFriends.exception.BancoDeDadosException;
import Game.friends.GameFriends.exception.RegraDeNegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    private Map<String, Object> buildBody(HttpStatus status, Object message, String path) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("message", message);
        body.put("path", path);
        return body;
    }

    // Trata erros de validação (@Valid) nos argumentos dos métodos
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.toList());

        String path = request.getDescription(false).replace("uri=", "");

        Map<String, Object> body = buildBody(status, errors, path);

        return new ResponseEntity<>(body, headers, status);
    }

    // Trata exceção personalizada de regra de negócio
    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<Object> handleRegraDeNegocioException(RegraDeNegocioException ex,
                                                                HttpServletRequest request) {
        Map<String, Object> body = buildBody(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Trata exceções de violações de constraints (ex: @Size, @NotNull em parâmetros)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
                                                                     HttpServletRequest request) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.toList());

        Map<String, Object> body = buildBody(HttpStatus.BAD_REQUEST, errors, request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Trata exceções específicas de banco de dados (ex: falha grave)
    @ExceptionHandler(BancoDeDadosException.class)
    public ResponseEntity<Object> handleBancoDeDadosException(BancoDeDadosException ex,
                                                              HttpServletRequest request) {
        Map<String, Object> body = buildBody(HttpStatus.GONE, ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.GONE);
    }

    // Trata todas as outras exceções não tratadas explicitamente
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex,
                                                      HttpServletRequest request) {
        String message = "Erro inesperado: " + ex.getMessage();
        Map<String, Object> body = buildBody(HttpStatus.INTERNAL_SERVER_ERROR, message, request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
