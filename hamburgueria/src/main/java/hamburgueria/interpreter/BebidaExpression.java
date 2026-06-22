package hamburgueria.interpreter;

import hamburgueria.produto.Bebida;
import hamburgueria.produto.ItemMenu;
import hamburgueria.produto.Refrigerante;
import hamburgueria.produto.SucoNatural;

/**
 * Expressão Terminal para bebidas na nossa DSL.
 */
public class BebidaExpression implements Expression {
    private final String tipo;

    public BebidaExpression(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public ItemMenu interpretar() {
        if (tipo.equalsIgnoreCase("refrigerante") || tipo.equalsIgnoreCase("refri")) {
            return new Refrigerante();
        } else if (tipo.equalsIgnoreCase("suco")) {
            return new SucoNatural();
        }
        throw new IllegalArgumentException("Bebida desconhecida: " + tipo);
    }
}
