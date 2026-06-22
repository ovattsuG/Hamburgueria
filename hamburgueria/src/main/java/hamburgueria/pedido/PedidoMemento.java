package hamburgueria.pedido;

import hamburgueria.desconto.DescontoStrategy;
import hamburgueria.fidelidade.FidelidadeStrategy;
import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.produto.ItemMenu;
import java.util.ArrayList;
import java.util.List;

/**
 * Memento no padrão Memento (GoF).
 * Armazena de forma imutável o estado interno de um Pedido (itens, pagamento, desconto e fidelidade).
 */
public class PedidoMemento {
    private final List<ItemMenu> itens;
    private final FormaPagamento formaPagamento;
    private final DescontoStrategy descontoStrategy;
    private final FidelidadeStrategy fidelidadeStrategy;

    public PedidoMemento(List<ItemMenu> itens,
                         FormaPagamento formaPagamento,
                         DescontoStrategy descontoStrategy,
                         FidelidadeStrategy fidelidadeStrategy) {
        this.itens = new ArrayList<>(itens);
        this.formaPagamento = formaPagamento;
        this.descontoStrategy = descontoStrategy;
        this.fidelidadeStrategy = fidelidadeStrategy;
    }

    public List<ItemMenu> getItens() {
        return itens;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public DescontoStrategy getDescontoStrategy() {
        return descontoStrategy;
    }

    public FidelidadeStrategy getFidelidadeStrategy() {
        return fidelidadeStrategy;
    }
}
