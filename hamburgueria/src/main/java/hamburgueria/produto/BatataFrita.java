package hamburgueria.produto;

/**
 * Acompanhamento concreto: Porção de Batata Frita.
 */
public class BatataFrita implements Acompanhamento {

    @Override
    public String emitir() {
        return "Porção de Batata Frita";
    }
}
