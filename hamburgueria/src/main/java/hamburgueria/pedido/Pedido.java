package hamburgueria.pedido;

import hamburgueria.desconto.DescontoStrategy;
import hamburgueria.desconto.SemDesconto;
import hamburgueria.fidelidade.FidelidadeStrategy;
import hamburgueria.fidelidade.SemFidelidade;
import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.produto.ItemMenu;
import java.util.Iterator;

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

    private PedidoState estadoAtual = new PedidoRecebidoState();
    private final java.util.List<PedidoObserver> observers = new java.util.ArrayList<>();
    private final java.util.List<ItemMenu> itens = new java.util.ArrayList<>();

    public void adicionarItem(ItemMenu item) {
        this.itens.add(item);
    }

    public java.util.List<ItemMenu> getItens() {
        return new java.util.ArrayList<>(itens);
    }

    public double getValorTotalItens() {
        double total = 0.0;
        for (ItemMenu item : itens) {
            total += item.getPreco();
        }
        return total;
    }

    public Iterator<ItemMenu> criarDeepIterator() {
        return new PedidoDeepIterator(itens);
    }

    public PedidoState getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(PedidoState estadoAtual) {
        this.estadoAtual = estadoAtual;
        notificarObservers();
    }

    public void registrarObserver(PedidoObserver observer) {
        this.observers.add(observer);
    }

    public void removerObserver(PedidoObserver observer) {
        this.observers.remove(observer);
    }

    private void notificarObservers() {
        String status = getDescricaoStatus();
        for (PedidoObserver observer : observers) {
            observer.onStatusChanged(status);
        }
    }

    public void avancarEstado() {
        this.estadoAtual.avancar(this);
    }

    public void cancelarPedidoState() {
        this.estadoAtual.cancelar(this);
    }

    public String getDescricaoStatus() {
        return this.estadoAtual.getStatus();
    }

    public FormaPagamento getFormaPagamento() {
        return this.formaPagamento;
    }

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
     * Template Method: define o esqueleto do algoritmo de fechamento de pedido.
     * Subclasses não podem alterar o esqueleto (final), mas customizam a taxa adicional.
     */
    public final double finalizarPedido(double valorItens) {
        double valorComDesconto = descontoStrategy.aplicar(valorItens);
        double valorComFidelidade = fidelidadeStrategy.aplicarBeneficio(valorComDesconto);
        double taxaAdicional = getTaxaAdicional();
        return formaPagamento.cobrar(valorComFidelidade + taxaAdicional);
    }

    /**
     * Método Gancho (Hook) / Primitivo a ser implementado pelas subclasses.
     */
    protected abstract double getTaxaAdicional();

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setDescontoStrategy(DescontoStrategy descontoStrategy) {
        this.descontoStrategy = descontoStrategy;
    }

    public void setFidelidadeStrategy(FidelidadeStrategy fidelidadeStrategy) {
        this.fidelidadeStrategy = fidelidadeStrategy;
    }

    public PedidoMemento salvarMemento() {
        return new PedidoMemento(this.itens, this.formaPagamento, this.descontoStrategy, this.fidelidadeStrategy);
    }

    public void restaurarMemento(PedidoMemento memento) {
        this.itens.clear();
        this.itens.addAll(memento.getItens());
        this.formaPagamento = memento.getFormaPagamento();
        this.descontoStrategy = memento.getDescontoStrategy();
        this.fidelidadeStrategy = memento.getFidelidadeStrategy();
    }
}
