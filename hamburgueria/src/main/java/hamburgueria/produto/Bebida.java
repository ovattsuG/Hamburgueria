package hamburgueria.produto;

/**
 * Interface para bebidas do cardápio.
 * Produto abstrato da Abstract Factory (ComboFactory).
 */
public interface Bebida extends ItemMenu {
    @Override
    default void aceitar(ItemVisitor visitor) {
        visitor.visitarBebida(this);
    }
}
