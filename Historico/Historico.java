package Historico;
import Modelo.Pontuacao;

public class Historico {

    private static final int CAPACIDADE = 10;

    private final Pontuacao[] registros = new Pontuacao[CAPACIDADE];
    private int total = 0;

    public void adicionar(Pontuacao pontuacao) {
        if (total == CAPACIDADE) {
            System.arraycopy(registros, 1, registros, 0, CAPACIDADE - 1);
            registros[CAPACIDADE - 1] = pontuacao;
        } else {
            registros[total++] = pontuacao;
        }
    }

    public void exibir() {
        System.out.println("\n===== HISTÓRICO (últimas " + CAPACIDADE + " partidas) =====");
        if (total == 0) {
            System.out.println("Nenhuma partida jogada ainda.");
            return;
        }
        for (int i = total - 1; i >= 0; i--) {
            System.out.printf("%2d. %s%n", total - i, registros[i]);
        }
    }
}
