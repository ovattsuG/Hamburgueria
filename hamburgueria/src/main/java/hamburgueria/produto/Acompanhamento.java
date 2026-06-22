package hamburgueria.produto;

/**
 * Interface para acompanhamentos do cardápio.
 * Produto do Factory Method (AcompanhamentoFactory).
 */
public interface Acompanhamento extends ItemMenu {
    String emitir();

    @Override
    default void aceitar(ItemVisitor visitor) {
        visitor.visitarAcompanhamento(this);
    }
}
