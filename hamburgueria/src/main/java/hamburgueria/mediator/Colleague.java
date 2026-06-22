package hamburgueria.mediator;

/**
 * Classe base abstrata para os colegas (Colleagues) do padrão Mediator.
 */
public abstract class Colleague {
    protected CozinhaMediator mediator;

    public Colleague(CozinhaMediator mediator) {
        this.mediator = mediator;
    }
}
