package hamburgueria.interpreter;

/**
 * Parser simples que lê expressões textuais do usuário e monta a AST correspondente.
 */
public class PedidoParser {

    public static Expression parse(String expressao) {
        String[] partes = expressao.split("\\+");
        Expression resultado = null;

        for (String parte : partes) {
            parte = parte.trim().toLowerCase();
            Expression atual = parseToken(parte);
            if (resultado == null) {
                resultado = atual;
            } else {
                resultado = new SomaExpression(resultado, atual);
            }
        }
        return resultado;
    }

    private static Expression parseToken(String token) {
        if (token.startsWith("lanche ")) {
            return new LancheExpression(token.substring(7).trim());
        } else if (token.startsWith("bebida ")) {
            return new BebidaExpression(token.substring(7).trim());
        } else if (token.startsWith("acompanhamento ")) {
            return new AcompanhamentoExpression(token.substring(15).trim());
        }
        throw new IllegalArgumentException("Token inválido: " + token);
    }
}
