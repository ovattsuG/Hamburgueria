package hamburgueria.combo;

import hamburgueria.produto.Acompanhamento;
import hamburgueria.produto.BatataFrita;
import hamburgueria.produto.OnionRings;

/**
 * Factory Method simplificado para criação de Acompanhamentos.
 * Padrão Factory Method (GoF).
 */
public class AcompanhamentoFactory {

    public static Acompanhamento criar(String tipo) {
        if (tipo.equalsIgnoreCase("Batata")) return new BatataFrita();
        if (tipo.equalsIgnoreCase("Cebola")) return new OnionRings();
        throw new IllegalArgumentException("Acompanhamento inválido: " + tipo);
    }
}
