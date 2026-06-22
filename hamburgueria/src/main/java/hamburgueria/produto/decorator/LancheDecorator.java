package hamburgueria.produto.decorator;

import hamburgueria.produto.Lanche;

/**
 * Decorator abstrato para lanches.
 * Padrão Decorator (GoF) — permite adicionar ingredientes dinamicamente.
 */
public abstract class LancheDecorator implements Lanche {

    protected Lanche lancheDecorado;

    public LancheDecorator(Lanche lanche) {
        this.lancheDecorado = lanche;
    }

    @Override
    public String getDescricao() {
        return lancheDecorado.getDescricao();
    }

    @Override
    public double getPreco() {
        return lancheDecorado.getPreco();
    }

    @Override
    public abstract Lanche clonar();
}
