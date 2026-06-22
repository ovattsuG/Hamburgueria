package hamburgueria.interpreter;

import hamburgueria.produto.ComboComposite;
import hamburgueria.produto.ItemMenu;

/**
 * Expressão Não-Terminal que combina e soma duas expressões sob um mesmo combo.
 */
public class SomaExpression implements Expression {
    private final Expression esquerda;
    private final Expression direita;

    public SomaExpression(Expression esquerda, Expression direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public ItemMenu interpretar() {
        ItemMenu itemEsq = esquerda.interpretar();
        ItemMenu itemDir = direita.interpretar();

        ComboComposite combo = new ComboComposite("Combo Customizado");

        // Desembrulha combos se as subexpressões já forem compostas
        if (itemEsq instanceof ComboComposite) {
            for (ItemMenu sub : ((ComboComposite) itemEsq).getItens()) {
                combo.adicionar(sub);
            }
        } else {
            combo.adicionar(itemEsq);
        }

        if (itemDir instanceof ComboComposite) {
            for (ItemMenu sub : ((ComboComposite) itemDir).getItens()) {
                combo.adicionar(sub);
            }
        } else {
            combo.adicionar(itemDir);
        }

        return combo;
    }
}
