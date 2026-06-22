package hamburgueria.pedido;

/**
 * ConcreteObserver: Aplicativo móvel do cliente que recebe notificações push.
 */
public class AppClienteObserver implements PedidoObserver {

    private String ultimoStatus;

    @Override
    public void onStatusChanged(String novoStatus) {
        this.ultimoStatus = novoStatus;
        System.out.println("[APP CLIENTE] Enviando notificação Push: Seu pedido está " + novoStatus);
    }

    public String getUltimoStatus() {
        return ultimoStatus;
    }
}
