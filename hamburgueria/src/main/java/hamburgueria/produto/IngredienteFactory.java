package hamburgueria.produto;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Factory: Gerencia um pool de instâncias compartilháveis de IngredienteFlyweight,
 * garantindo que ingredientes com mesmo nome sejam criados apenas uma vez.
 */
public class IngredienteFactory {

    private static final Map<String, IngredienteFlyweight> pool = new HashMap<>();

    public static IngredienteFlyweight getIngrediente(String nome, int calorias) {
        if (!pool.containsKey(nome)) {
            pool.put(nome, new IngredienteFlyweight(nome, calorias));
        }
        return pool.get(nome);
    }

    public static int getQuantidadeNoPool() {
        return pool.size();
    }

    public static void limparPool() {
        pool.clear();
    }
}
