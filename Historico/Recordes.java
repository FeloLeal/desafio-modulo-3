package Historico;
import Modelo.Nivel;

public class Recordes {

    private final Nivel[] niveis;
    private final int[] melhores;

    public Recordes(Nivel[] niveis) {
        this.niveis = niveis;
        this.melhores = new int[niveis.length];
    }

    public boolean registrar(Nivel nivel, int pontos) {
        if (pontos > melhores[nivel.getIndice()]) {
            melhores[nivel.getIndice()] = pontos;
            return true;
        }
        return false;
    }

    public void exibir() {
        System.out.println("\n===== RECORDES POR NÍVEL =====");
        for (Nivel nivel : niveis) {
            int pontos = melhores[nivel.getIndice()];
            String valor = pontos > 0 ? pontos + " pts" : "sem registro";
            System.out.printf("%-8s: %s%n", nivel.getNome(), valor);
        }
    }
}
