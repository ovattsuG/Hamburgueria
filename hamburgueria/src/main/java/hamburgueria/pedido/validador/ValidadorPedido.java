package hamburgueria.pedido.validador;

import hamburgueria.pedido.Pedido;

/**
 * Classe base (Handler) para a cadeia de validação de pedidos.
 * Padrão Chain of Responsibility (GoF).
 */
public abstract class ValidadorPedido {
    protected ValidadorPedido proximo;

    public ValidadorPedido setProximo(ValidadorPedido proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public abstract void validar(Pedido pedido);

    protected void validarProximo(Pedido pedido) {
        if (proximo != null) {
            proximo.validar(pedido);
        }
    }
}
