package hamburgueria.pedido;

import hamburgueria.desconto.DescontoStrategy;
import hamburgueria.desconto.SemDesconto;
import hamburgueria.fidelidade.FidelidadeStrategy;
import hamburgueria.fidelidade.SemFidelidade;
import hamburgueria.pagamento.FormaPagamento;

/**
 * Builder para Pedido.
 * Permite a construção passo a passo de um Pedido de forma fluida.
 */
public class PedidoBuilder {

    private String tipoPedido;
    private FormaPagamento formaPagamento;
    private DescontoStrategy descontoStrategy = new SemDesconto();
    private FidelidadeStrategy fidelidadeStrategy = new SemFidelidade();

    private final java.util.List<hamburgueria.produto.ItemMenu> itens = new java.util.ArrayList<>();

    public PedidoBuilder paraDelivery() {
        this.tipoPedido = "DELIVERY";
        return this;
    }

    public PedidoBuilder paraSalao() {
        this.tipoPedido = "SALAO";
        return this;
    }

    public PedidoBuilder comFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
        return this;
    }

    public PedidoBuilder comDesconto(DescontoStrategy descontoStrategy) {
        this.descontoStrategy = descontoStrategy;
        return this;
    }

    public PedidoBuilder comFidelidade(FidelidadeStrategy fidelidadeStrategy) {
        this.fidelidadeStrategy = fidelidadeStrategy;
        return this;
    }

    public PedidoBuilder comItem(hamburgueria.produto.ItemMenu item) {
        this.itens.add(item);
        return this;
    }

    public Pedido build() {
        if (tipoPedido == null) {
            throw new IllegalStateException("O tipo de pedido (Delivery ou Salão) deve ser especificado.");
        }
        if (formaPagamento == null) {
            throw new IllegalStateException("A forma de pagamento deve ser especificada.");
        }

        Pedido pedido;
        if (tipoPedido.equals("DELIVERY")) {
            pedido = new PedidoDelivery(formaPagamento, descontoStrategy, fidelidadeStrategy);
        } else {
            pedido = new PedidoSalao(formaPagamento, descontoStrategy, fidelidadeStrategy);
        }

        for (hamburgueria.produto.ItemMenu item : itens) {
            pedido.adicionarItem(item);
        }

        return pedido;
    }
}
