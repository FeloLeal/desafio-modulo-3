package Partida;
import java.util.Random;

import Menu.Entrada;
import Modelo.Nivel;

public abstract class Partida {

    private static final int PENALIDADE_POR_TENTATIVA = 10;
    private static final int BONUS_POR_TENTATIVA_RESTANTE = 50;

    protected final Nivel nivel;
    protected final Entrada entrada;
    protected final Random random = new Random();

    protected int tentativasUsadas = 0;
    protected int custoDicas = 0;

    protected Partida(Nivel nivel, Entrada entrada) {
        this.nivel = nivel;
        this.entrada = entrada;
    }

    public abstract boolean jogar();

    public abstract String getModo();

    public Nivel getNivel() {
        return nivel;
    }

    public int calcularPontuacao(boolean venceu) {
        if (!venceu) {
            return 0;
        }
        int restantes = nivel.getTentativas() - tentativasUsadas;
        int pontos = nivel.getPontuacaoBase()
                - tentativasUsadas * PENALIDADE_POR_TENTATIVA
                + restantes * BONUS_POR_TENTATIVA_RESTANTE
                - custoDicas;
        return Math.max(pontos, 0);
    }
}
