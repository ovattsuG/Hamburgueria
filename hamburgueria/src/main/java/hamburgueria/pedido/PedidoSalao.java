package hamburgueria.pedido;

import hamburgueria.desconto.DescontoStrategy;
import hamburgueria.fidelidade.FidelidadeStrategy;
import hamburgueria.pagamento.FormaPagamento;

/**
 * Pedido para consumo no salão — sem taxa de entrega.
 *
 * Pipeline: valorItens → desconto → fidelidade → pagamento
 */
public class PedidoSalao extends Pedido {

    public PedidoSalao(FormaPagamento fp) {
        super(fp);
    }

    public PedidoSalao(FormaPagamento fp, DescontoStrategy ds, FidelidadeStrategy fs) {
        super(fp, ds, fs);
    }

    @Override
    public double finalizarPedido(double valorItens) {
        double valorComDesconto = descontoStrategy.aplicar(valorItens);
        double valorComFidelidade = fidelidadeStrategy.aplicarBeneficio(valorComDesconto);
        return formaPagamento.cobrar(valorComFidelidade);
    }
}
