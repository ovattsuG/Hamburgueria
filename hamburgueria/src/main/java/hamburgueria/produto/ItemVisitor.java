package hamburgueria.produto;

/**
 * Interface Visitor no padrão Visitor (GoF).
 * Define operações de visita para os diferentes tipos de produtos na hamburgueria.
 */
public interface ItemVisitor {
    void visitarLanche(Lanche lanche);
    void visitarBebida(Bebida bebida);
    void visitarAcompanhamento(Acompanhamento acompanhamento);
    void visitarCombo(ComboComposite combo);
}
