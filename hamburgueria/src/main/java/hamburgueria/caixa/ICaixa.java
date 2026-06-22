package hamburgueria.caixa;

/**
 * Subject: Interface comum para o caixa.
 * Permite que o Proxy de proteção/auditoria seja injetado ou utilizado
 * de forma transparente no lugar da instância real.
 */
public interface ICaixa {
    void registrarVenda(double valor);
    double getSaldoTotal();
    void zerarCaixa();
}
