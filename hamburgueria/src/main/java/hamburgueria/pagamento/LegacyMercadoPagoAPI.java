package hamburgueria.pagamento;

/**
 * API externa legada de pagamentos.
 * Representa uma classe com interface incompatível com a nossa interface FormaPagamento.
 */
public class LegacyMercadoPagoAPI {

    private double ultimoValorProcessado = 0.0;
    private String ultimoTokenUsado = null;

    public void processarTransacao(double valorTotal, String token) {
        this.ultimoValorProcessado = valorTotal;
        this.ultimoTokenUsado = token;
        System.out.println("Processando R$ " + valorTotal + " via Mercado Pago (token: " + token + ")");
    }

    public double getUltimoValorProcessado() {
        return ultimoValorProcessado;
    }

    public String getUltimoTokenUsado() {
        return ultimoTokenUsado;
    }
}
