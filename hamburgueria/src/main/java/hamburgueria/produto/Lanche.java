package hamburgueria.produto;

/**
 * Interface base para todos os lanches da hamburgueria.
 * Componente do padrão Decorator — define o contrato comum.
 */
public interface Lanche {
    String getDescricao();
    double getPreco();
}
