package hamburgueria.produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite: representa um combo composto por múltiplos itens do cardápio.
 * Calcula o preço do combo aplicando um desconto didático (ex: 15%).
 */
public class ComboComposite implements ItemMenu {

    private final String nome;
    private final List<ItemMenu> itens = new ArrayList<>();

    public ComboComposite(String nome) {
        this.nome = nome;
    }

    public void adicionar(ItemMenu item) {
        this.itens.add(item);
    }

    public void remover(ItemMenu item) {
        this.itens.remove(item);
    }

    public List<ItemMenu> getItens() {
        return new ArrayList<>(itens);
    }

    @Override
    public String getDescricao() {
        StringBuilder sb = new StringBuilder(nome).append(" (");
        for (int i = 0; i < itens.size(); i++) {
            sb.append(itens.get(i).getDescricao());
            if (i < itens.size() - 1) {
                sb.append(" + ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public double getPreco() {
        double total = 0.0;
        for (ItemMenu item : itens) {
            total += item.getPreco();
        }
        // Aplica 15% de desconto didático para o combo
        double precoComDesconto = total * 0.85;
        // Arredonda para duas casas decimais
        return Math.round(precoComDesconto * 100.0) / 100.0;
     }

    @Override
    public void aceitar(ItemVisitor visitor) {
        visitor.visitarCombo(this);
    }
}
