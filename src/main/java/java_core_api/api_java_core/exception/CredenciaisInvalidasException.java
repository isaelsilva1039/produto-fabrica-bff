package java_core_api.api_java_core.exception;

public class CredenciaisInvalidasException extends RuntimeException {
    public CredenciaisInvalidasException() {
        super("Usuário ou senha inválidos");
    }

    public CredenciaisInvalidasException(String message) {
        super(message);
    }
}
