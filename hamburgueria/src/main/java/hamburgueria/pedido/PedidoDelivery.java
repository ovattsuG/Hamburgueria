package hamburgueria.pedido;

import hamburgueria.desconto.DescontoStrategy;
import hamburgueria.fidelidade.FidelidadeStrategy;
import hamburgueria.pagamento.FormaPagamento;

/**
 * Pedido para delivery — com taxa de entrega de R$ 10,00.
 * Se o cliente tem fidelidade com frete grátis, a taxa é zerada.
 *
 * Pipeline: valorItens → desconto → fidelidade → (+frete?) → pagamento
 */
public class PedidoDelivery extends Pedido {

    private static final double TAXA_ENTREGA = 10.0;

    public PedidoDelivery(FormaPagamento fp) {
        super(fp);
    }

    public PedidoDelivery(FormaPagamento fp, DescontoStrategy ds, FidelidadeStrategy fs) {
        super(fp, ds, fs);
    }

    @Override
    protected double getTaxaAdicional() {
        return fidelidadeStrategy.isFreteGratis() ? 0.0 : TAXA_ENTREGA;
    }
}
