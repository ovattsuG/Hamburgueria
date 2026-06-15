package hamburgueria.combo;

import hamburgueria.produto.Bebida;
import hamburgueria.produto.Lanche;
import hamburgueria.produto.LancheCarne;
import hamburgueria.produto.Refrigerante;

/**
 * ConcreteFactory: cria Combo Tradicional (Carne + Refrigerante).
 */
public class ComboTradicionalFactory implements ComboFactory {

    @Override
    public Lanche criarLanche() {
        return new LancheCarne();
    }

    @Override
    public Bebida criarBebida() {
        return new Refrigerante();
    }
}
