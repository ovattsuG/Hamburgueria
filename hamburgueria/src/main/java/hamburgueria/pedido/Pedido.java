package hamburgueria.pedido;

import hamburgueria.desconto.DescontoStrategy;
import hamburgueria.desconto.SemDesconto;
import hamburgueria.fidelidade.FidelidadeStrategy;
import hamburgueria.fidelidade.SemFidelidade;
import hamburgueria.pagamento.FormaPagamento;

/**
 * Classe abstrata que orquestra a finalização de um pedido.
 * Padrão Bridge (GoF) — desacopla abstração (Pedido) da implementação (FormaPagamento).
 *
 * Agora composta com Strategy de Desconto e Fidelidade,
 * demonstrando Open/Closed Principle: novas políticas de desconto e fidelidade
 * podem ser adicionadas sem modificar esta classe.
 *
 * Pipeline de cálculo: valorItens → desconto → fidelidade → taxa de entrega → pagamento
 */
public abstract class Pedido {

    protected FormaPagamento formaPagamento;
    protected DescontoStrategy descontoStrategy;
    protected FidelidadeStrategy fidelidadeStrategy;

    /**
     * Construtor completo com todas as strategies.
     */
    public Pedido(FormaPagamento formaPagamento,
                  DescontoStrategy descontoStrategy,
                  FidelidadeStrategy fidelidadeStrategy) {
        this.formaPagamento = formaPagamento;
        this.descontoStrategy = descontoStrategy;
        this.fidelidadeStrategy = fidelidadeStrategy;
    }

    /**
     * Construtor retrocompatível — usa Null Objects (SemDesconto, SemFidelidade).
     */
    public Pedido(FormaPagamento formaPagamento) {
        this(formaPagamento, new SemDesconto(), new SemFidelidade());
    }

    /**
     * Finaliza o pedido, aplicando o pipeline completo de cálculo.
     *
     * @param valorItens valor bruto dos itens
     * @return valor final cobrado
     */
    public abstract double finalizarPedido(double valorItens);
}
