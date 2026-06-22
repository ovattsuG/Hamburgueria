package hamburgueria.produto;

/**
 * Flyweight: Armazena o estado intrínseco (nome e calorias do ingrediente)
 * que é imutável e pode ser compartilhado por múltiplos objetos.
 */
public class IngredienteFlyweight {

    private final String nome;
    private final int calorias;

    public IngredienteFlyweight(String nome, int calorias) {
        this.nome = nome;
        this.calorias = calorias;
    }

    public String getNome() {
        return nome;
    }

    public int getCalorias() {
        return calorias;
    }
}
