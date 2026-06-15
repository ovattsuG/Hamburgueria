package hamburgueria.produto;

/**
 * Lanche concreto: Hambúrguer de Carne Artesanal.
 * ConcreteComponent do padrão Decorator.
 */
public class LancheCarne implements Lanche {

    @Override
    public String getDescricao() {
        return "Hambúrguer de Carne Artesanal";
    }

    @Override
    public double getPreco() {
        return 25.0;
    }
}
