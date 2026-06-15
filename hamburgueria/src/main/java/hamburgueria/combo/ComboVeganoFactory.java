package hamburgueria.combo;

import hamburgueria.produto.Bebida;
import hamburgueria.produto.Lanche;
import hamburgueria.produto.LancheVegano;
import hamburgueria.produto.SucoNatural;

/**
 * ConcreteFactory: cria Combo Vegano (Grão de Bico + Suco Natural).
 */
public class ComboVeganoFactory implements ComboFactory {

    @Override
    public Lanche criarLanche() {
        return new LancheVegano();
    }

    @Override
    public Bebida criarBebida() {
        return new SucoNatural();
    }
}
