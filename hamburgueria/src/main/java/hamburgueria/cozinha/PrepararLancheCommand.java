package hamburgueria.cozinha;

import hamburgueria.produto.Lanche;

/**
 * Comando concreto para preparar um lanche.
 */
public class PrepararLancheCommand implements CozinhaCommand {
    private final Lanche lanche;
    private boolean preparado = false;

    public PrepararLancheCommand(Lanche lanche) {
        this.lanche = lanche;
    }

    @Override
    public void executar() {
        this.preparado = true;
        System.out.println("[COZINHA] Preparando lanche: " + lanche.getDescricao());
    }

    @Override
    public void desfazer() {
        this.preparado = false;
        System.out.println("[COZINHA] Cancelando preparação do lanche: " + lanche.getDescricao());
    }

    @Override
    public String getStatus() {
        return preparado ? "Preparado" : "Pendente";
    }

    public Lanche getLanche() {
        return lanche;
    }
}
