package hamburgueria.produto;

/**
 * Interface base (Component) para todos os itens do cardápio.
 * Permite tratar itens simples e combos de forma homogênea.
 */
public interface ItemMenu {
    String getDescricao();
    double getPreco();
    void aceitar(ItemVisitor visitor);
}
