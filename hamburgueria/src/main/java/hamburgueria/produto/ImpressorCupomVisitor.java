package hamburgueria.produto;

/**
 * Visitante concreto que gera uma string formatada de cupom fiscal para itens ou combos.
 */
public class ImpressorCupomVisitor implements ItemVisitor {
    private final StringBuilder sb = new StringBuilder();

    public String getCupom() {
        return sb.toString().trim();
    }

    @Override
    public void visitarLanche(Lanche lanche) {
        sb.append(String.format("Lanche: %s - R$ %.2f\n", lanche.getDescricao(), lanche.getPreco()));
    }

    @Override
    public void visitarBebida(Bebida bebida) {
        sb.append(String.format("Bebida: %s - R$ %.2f\n", bebida.getDescricao(), bebida.getPreco()));
    }

    @Override
    public void visitarAcompanhamento(Acompanhamento acompanhamento) {
        sb.append(String.format("Acomp:  %s - R$ %.2f\n", acompanhamento.getDescricao(), acompanhamento.getPreco()));
    }

    @Override
    public void visitarCombo(ComboComposite combo) {
        sb.append(String.format("Combo:  %s - R$ %.2f\n", combo.getDescricao(), combo.getPreco()));
        // Visita recursivamente os itens de dentro do combo para cupom detalhado
        for (ItemMenu item : combo.getItens()) {
            sb.append("  * ");
            item.aceitar(this);
        }
    }
}
