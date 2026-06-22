package hamburgueria.produto;

/**
 * Contexto do Padrão Flyweight: Combina a instância compartilhada (estado intrínseco)
 * com as propriedades específicas do uso naquele momento (estado extrínseco - quantidade).
 */
public class ItemIngrediente {

    private final IngredienteFlyweight ingrediente;
    private final int quantidade; // Estado extrínseco

    public ItemIngrediente(IngredienteFlyweight ingrediente, int quantidade) {
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    public double getCaloriasTotais() {
        return ingrediente.getCalorias() * quantidade;
    }

    public String getDetalhe() {
        return quantidade + "x " + ingrediente.getNome() + " (" + getCaloriasTotais() + " kcal)";
    }
}
