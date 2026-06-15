package hamburgueria.produto;

/**
 * Acompanhamento concreto: Porção de Anéis de Cebola.
 */
public class OnionRings implements Acompanhamento {

    @Override
    public String emitir() {
        return "Porção de Anéis de Cebola";
    }
}
