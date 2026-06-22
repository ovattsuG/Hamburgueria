package hamburgueria.pagamento;

/**
 * Adapter do padrão de projeto GoF.
 * Adapta a interface legada/externa LegacyMercadoPagoAPI à interface interna FormaPagamento.
 */
public class MercadoPagoAdapter implements FormaPagamento {

    private final LegacyMercadoPagoAPI apiExterna;
    private final String tokenAcesso;

    public MercadoPagoAdapter(LegacyMercadoPagoAPI apiExterna, String tokenAcesso) {
        this.apiExterna = apiExterna;
        this.tokenAcesso = tokenAcesso;
    }

    @Override
    public double cobrar(double valor) {
        // Adapta o fluxo traduzindo a chamada para a API externa
        apiExterna.processarTransacao(valor, tokenAcesso);
        return valor;
    }
}
