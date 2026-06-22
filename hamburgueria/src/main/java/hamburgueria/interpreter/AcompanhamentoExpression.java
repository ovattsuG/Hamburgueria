package hamburgueria.interpreter;

import hamburgueria.produto.ItemMenu;
import hamburgueria.produto.BatataFrita;
import hamburgueria.produto.OnionRings;

/**
 * Expressão Terminal para acompanhamentos na nossa DSL.
 */
public class AcompanhamentoExpression implements Expression {
    private final String tipo;

    public AcompanhamentoExpression(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public ItemMenu interpretar() {
        if (tipo.equalsIgnoreCase("batata")) {
            return new BatataFrita();
        } else if (tipo.equalsIgnoreCase("onion") || tipo.equalsIgnoreCase("cebola") || tipo.equalsIgnoreCase("onion rings")) {
            return new OnionRings();
        }
        throw new IllegalArgumentException("Acompanhamento desconhecido: " + tipo);
    }
}
