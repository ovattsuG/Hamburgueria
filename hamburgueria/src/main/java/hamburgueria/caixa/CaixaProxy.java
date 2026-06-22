package hamburgueria.caixa;

/**
 * Proxy de Proteção e Auditoria para o Caixa.
 * Controla o acesso ao método crítico zerarCaixa() com base no cargo do usuário,
 * e gera logs de auditoria para operações de vendas.
 */
public class CaixaProxy implements ICaixa {

    private final ICaixa caixaReal;
    private final String cargoUsuario;

    public CaixaProxy(String cargoUsuario) {
        this.caixaReal = Caixa.getInstancia();
        this.cargoUsuario = cargoUsuario;
    }

    @Override
    public void registrarVenda(double valor) {
        // Log de Auditoria
        System.out.println("[AUDITORIA] Operação de venda registrada no valor de R$ " + valor);
        caixaReal.registrarVenda(valor);
    }

    @Override
    public double getSaldoTotal() {
        return caixaReal.getSaldoTotal();
    }

    @Override
    public void zerarCaixa() {
        // Controle de Acesso (Proxy de Proteção)
        if ("GERENTE".equalsIgnoreCase(cargoUsuario)) {
            System.out.println("[AUDITORIA] Fechamento de caixa autorizado para o cargo: " + cargoUsuario);
            caixaReal.zerarCaixa();
        } else {
            System.out.println("[AUDITORIA][NEGADO] Tentativa não autorizada de zerar caixa por: " + cargoUsuario);
            throw new SecurityException("Acesso negado: Apenas funcionários com cargo de GERENTE podem zerar o caixa.");
        }
    }
}
