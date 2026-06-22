package hamburgueria.cozinha;

import hamburgueria.produto.Bebida;

/**
 * Comando concreto para preparar uma bebida.
 */
public class PrepararBebidaCommand implements CozinhaCommand {
    private final Bebida bebida;
    private boolean preparado = false;

    public PrepararBebidaCommand(Bebida bebida) {
        this.bebida = bebida;
    }

    @Override
    public void executar() {
        this.preparado = true;
        System.out.println("[COZINHA] Preparando bebida: " + bebida.getDescricao());
    }

    @Override
    public void desfazer() {
        this.preparado = false;
        System.out.println("[COZINHA] Cancelando preparação da bebida: " + bebida.getDescricao());
    }

    @Override
    public String getStatus() {
        return preparado ? "Preparado" : "Pendente";
    }

    public Bebida getBebida() {
        return bebida;
    }
}
