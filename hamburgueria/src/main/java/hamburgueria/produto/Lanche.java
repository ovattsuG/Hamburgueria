package hamburgueria.produto;

/**
 * Interface base para todos os lanches da hamburgueria.
 * Componente do padrão Decorator — define o contrato comum.
 */
public interface Lanche extends ItemMenu {
    Lanche clonar();

    @Override
    default void aceitar(ItemVisitor visitor) {
        visitor.visitarLanche(this);
    }
}
