package hamburgueria.pagamento;

import hamburgueria.caixa.Caixa;

/**
 * ConcreteStrategy: Pagamento via Cartão com taxa de 5%.
 */
public class PagamentoCartao implements FormaPagamento {

    @Override
    public double cobrar(double valor) {
        double valorFinal = valor * 1.05; // 5% de taxa
        Caixa.getInstancia().registrarVenda(valorFinal);
        return valorFinal;
    }
}
