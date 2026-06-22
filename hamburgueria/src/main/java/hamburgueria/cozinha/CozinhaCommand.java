package hamburgueria.cozinha;

/**
 * Interface que representa o Command no padrão Command (GoF).
 * Desacopla a solicitação do preparo de itens da sua execução física na cozinha.
 */
public interface CozinhaCommand {
    void executar();
    void desfazer();
    String getStatus();
}
