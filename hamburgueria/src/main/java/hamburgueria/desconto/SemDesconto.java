package hamburgueria.desconto;

/**
 * ConcreteStrategy: Nenhum desconto aplicado (Null Object Pattern).
 * Valor de retorno é exatamente o valor original.
 */
public class SemDesconto implements DescontoStrategy {

    @Override
    public double aplicar(double valorOriginal) {
        return valorOriginal;
    }

    @Override
    public String getDescricao() {
        return "Sem desconto";
    }
}
