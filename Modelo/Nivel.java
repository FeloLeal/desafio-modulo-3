package Modelo;
public class Nivel {

    private static final String[] NOMES = {"Fácil", "Médio", "Difícil"};
    private static final int[][] CONFIGURACOES = {
            {50, 10, 100},
            {100, 7, 200},
            {200, 5, 300}
    };

    private final int indice;
    private final String nome;
    private final int limite;
    private final int tentativas;
    private final int pontuacaoBase;

    private Nivel(int indice) {
        this.indice = indice;
        this.nome = NOMES[indice];
        this.limite = CONFIGURACOES[indice][0];
        this.tentativas = CONFIGURACOES[indice][1];
        this.pontuacaoBase = CONFIGURACOES[indice][2];
    }

    public static Nivel[] todos() {
        Nivel[] niveis = new Nivel[NOMES.length];
        for (int i = 0; i < niveis.length; i++) {
            niveis[i] = new Nivel(i);
        }
        return niveis;
    }

    public int getIndice() {
        return indice;
    }

    public String getNome() {
        return nome;
    }

    public int getLimite() {
        return limite;
    }

    public int getTentativas() {
        return tentativas;
    }

    public int getPontuacaoBase() {
        return pontuacaoBase;
    }

    @Override
    public String toString() {
        return String.format("%s (1 a %d, %d tentativas, base %d pts)", nome, limite, tentativas, pontuacaoBase);
    }
}
