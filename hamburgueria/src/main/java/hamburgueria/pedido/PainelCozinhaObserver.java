package hamburgueria.pedido;

/**
 * ConcreteObserver: Painel da cozinha que monitora os status dos pedidos.
 */
public class PainelCozinhaObserver implements PedidoObserver {

    private String ultimoStatus;

    @Override
    public void onStatusChanged(String novoStatus) {
        this.ultimoStatus = novoStatus;
        System.out.println("[PAINEL COZINHA] Pedido alterado para status: " + novoStatus);
    }

    public String getUltimoStatus() {
        return ultimoStatus;
    }
}
