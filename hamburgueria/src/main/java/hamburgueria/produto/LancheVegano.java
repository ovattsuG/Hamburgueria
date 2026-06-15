package hamburgueria.produto;

/**
 * Lanche concreto: Hambúrguer de Grão de Bico.
 * ConcreteComponent do padrão Decorator.
 */
public class LancheVegano implements Lanche {

    @Override
    public String getDescricao() {
        return "Hambúrguer de Grão de Bico";
    }

    @Override
    public double getPreco() {
        return 28.0;
    }
}
