package hamburgueria.produto;

/**
 * Bebida concreta: Refrigerante Cola.
 */
public class Refrigerante implements Bebida {

    @Override
    public String getDescricao() {
        return "Refrigerante Cola";
    }

    @Override
    public double getPreco() {
        return 8.00;
    }
}
