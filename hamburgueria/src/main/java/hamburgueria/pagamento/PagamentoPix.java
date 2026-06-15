package hamburgueria.pagamento;

import hamburgueria.caixa.Caixa;

/**
 * ConcreteStrategy: Pagamento via Pix com 10% de desconto.
 */
public class PagamentoPix implements FormaPagamento {

    @Override
    public double cobrar(double valor) {
        double valorFinal = valor * 0.90; // 10% desconto
        Caixa.getInstancia().registrarVenda(valorFinal);
        return valorFinal;
    }
}
