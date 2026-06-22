package hamburgueria.produto;

/**
 * Visitante concreto que calcula o total de calorias de um item ou combo.
 */
public class CalculadorCaloriasVisitor implements ItemVisitor {
    private int totalCalorias = 0;

    public int getTotalCalorias() {
        return totalCalorias;
    }

    @Override
    public void visitarLanche(Lanche lanche) {
        if (lanche.getDescricao().contains("Vegano")) {
            totalCalorias += 400;
        } else {
            totalCalorias += 600;
        }
        // Soma calorias para adicionais do Decorator se presentes
        if (lanche.getDescricao().contains("Queijo")) {
            totalCalorias += 100;
        }
        if (lanche.getDescricao().contains("Bacon")) {
            totalCalorias += 150;
        }
    }

    @Override
    public void visitarBebida(Bebida bebida) {
        if (bebida.getDescricao().contains("Refrigerante")) {
            totalCalorias += 150;
        } else {
            totalCalorias += 100;
        }
    }

    @Override
    public void visitarAcompanhamento(Acompanhamento acompanhamento) {
        if (acompanhamento.getDescricao().contains("Batata")) {
            totalCalorias += 300;
        } else {
            totalCalorias += 250;
        }
    }

    @Override
    public void visitarCombo(ComboComposite combo) {
        // Navega recursivamente dentro dos itens do combo
        for (ItemMenu item : combo.getItens()) {
            item.aceitar(this);
        }
    }
}
