package hamburgueria.produto.decorator;

import hamburgueria.produto.Lanche;

/**
 * ConcreteDecorator: adiciona Bacon Crocante (+R$ 5,00).
 */
public class AdicionalBacon extends LancheDecorator {

    public AdicionalBacon(Lanche lanche) {
        super(lanche);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Bacon Crocante";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 5.0;
    }
}
