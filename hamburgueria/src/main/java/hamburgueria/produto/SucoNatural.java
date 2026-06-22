package hamburgueria.produto;

/**
 * Bebida concreta: Suco Natural de Laranja.
 */
public class SucoNatural implements Bebida {

    @Override
    public String getDescricao() {
        return "Suco Natural de Laranja";
    }

    @Override
    public double getPreco() {
        return 12.00;
    }
}
