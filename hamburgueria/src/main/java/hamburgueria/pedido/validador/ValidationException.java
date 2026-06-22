package hamburgueria.pedido.validador;

/**
 * Exceção de validação lançada pela Cadeia de Responsabilidade (Chain of Responsibility).
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
