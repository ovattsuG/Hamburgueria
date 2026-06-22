package hamburgueria.interpreter;

import hamburgueria.produto.ItemMenu;
import hamburgueria.produto.LancheCarne;
import hamburgueria.produto.LancheVegano;

/**
 * Expressão Terminal para lanches na nossa DSL.
 */
public class LancheExpression implements Expression {
    private final String tipo;

    public LancheExpression(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public ItemMenu interpretar() {
        if (tipo.equalsIgnoreCase("carne")) {
            return new LancheCarne();
        } else if (tipo.equalsIgnoreCase("vegano")) {
            return new LancheVegano();
        }
        throw new IllegalArgumentException("Lanche desconhecido: " + tipo);
    }
}
