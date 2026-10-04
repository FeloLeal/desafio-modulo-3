package Modelo;
public record Pontuacao(int pontos, String nivel, String modo, boolean venceu) {

    @Override
    public String toString() {
        return String.format("%-8s | %-10s | %-9s | %d pts", nivel, modo, venceu ? "Vitória" : "Derrota", pontos);
    }
}
