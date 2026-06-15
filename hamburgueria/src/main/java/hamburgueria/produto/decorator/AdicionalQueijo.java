package hamburgueria.produto.decorator;

import hamburgueria.produto.Lanche;

/**
 * ConcreteDecorator: adiciona Queijo Cheddar (+R$ 3,50).
 */
public class AdicionalQueijo extends LancheDecorator {

    public AdicionalQueijo(Lanche lanche) {
        super(lanche);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Queijo Cheddar";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 3.5;
    }
}
