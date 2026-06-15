package hamburgueria.combo;

import hamburgueria.produto.Lanche;
import hamburgueria.produto.Bebida;

/**
 * Abstract Factory: interface para criação de famílias de combos.
 * Padrão Abstract Factory (GoF).
 */
public interface ComboFactory {
    Lanche criarLanche();
    Bebida criarBebida();
}
