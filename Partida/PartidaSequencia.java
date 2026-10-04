package Partida;
import Menu.Entrada;
import Modelo.Nivel;

public class PartidaSequencia extends Partida {

    private static final int TAMANHO = 3;

    public PartidaSequencia(Nivel nivel, Entrada entrada) {
        super(nivel, entrada);
    }

    @Override
    public String getModo() {
        return "Sequência";
    }

    @Override
    public boolean jogar() {
        int[] segredo = new int[TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            segredo[i] = random.nextInt(nivel.getLimite()) + 1;
        }

        System.out.printf("%nGerei uma sequência de %d números entre 1 e %d. Você tem %d tentativas.%n",
                TAMANHO, nivel.getLimite(), nivel.getTentativas());
        System.out.println("Digite os 3 números separados por espaço (ex: 5 8 2).");

        while (tentativasUsadas < nivel.getTentativas()) {
            int[] palpite = lerPalpite();
            tentativasUsadas++;

            int acertos = 0;
            StringBuilder resultado = new StringBuilder();
            for (int i = 0; i < TAMANHO; i++) {
                resultado.append(String.format("  Posição %d: ", i + 1));
                if (palpite[i] == segredo[i]) {
                    acertos++;
                    resultado.append("correto%n");
                } else if (palpite[i] < segredo[i]) {
                    resultado.append("o número é maior%n");
                } else {
                    resultado.append("o número é menor%n");
                }
            }

            if (acertos == TAMANHO) {
                System.out.printf("Acertou a sequência em %d tentativa(s)!%n", tentativasUsadas);
                return true;
            }
            System.out.printf("Acertos: %d/%d%n", acertos, TAMANHO);
            System.out.printf(resultado.toString());
        }

        System.out.println("Suas tentativas acabaram. A sequência era "
                + segredo[0] + " " + segredo[1] + " " + segredo[2] + ".");
        return false;
    }

    private int[] lerPalpite() {
        while (true) {
            String texto = entrada.lerTexto(String.format("Tentativa %d/%d: ",
                    tentativasUsadas + 1, nivel.getTentativas()));
            String[] partes = texto.split("\\s+");

            if (partes.length != TAMANHO) {
                System.out.println("Informe exatamente " + TAMANHO + " números.");
                continue;
            }

            int[] palpite = new int[TAMANHO];
            boolean valido = true;
            for (int i = 0; i < TAMANHO && valido; i++) {
                try {
                    palpite[i] = Integer.parseInt(partes[i]);
                    if (palpite[i] < 1 || palpite[i] > nivel.getLimite()) {
                        System.out.printf("Os números devem estar entre 1 e %d.%n", nivel.getLimite());
                        valido = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Use apenas números.");
                    valido = false;
                }
            }

            if (valido) {
                return palpite;
            }
        }
    }
}
