package Partida;
import Menu.Entrada;
import Modelo.Nivel;

public class PartidaClassica extends Partida {

    private static final int CUSTO_PARIDADE = 10;
    private static final int CUSTO_INTERVALO = 20;
    private static final int CUSTO_PROXIMIDADE = 15;

    private int ultimoPalpite = -1;

    public PartidaClassica(Nivel nivel, Entrada entrada) {
        super(nivel, entrada);
    }

    @Override
    public String getModo() {
        return "Clássico";
    }

    @Override
    public boolean jogar() {
        int segredo = random.nextInt(nivel.getLimite()) + 1;

        System.out.printf("%nPensei em um número de 1 a %d. Você tem %d tentativas.%n",
                nivel.getLimite(), nivel.getTentativas());
        System.out.println("Digite D a qualquer momento para pedir uma dica.");

        while (tentativasUsadas < nivel.getTentativas()) {
            String texto = entrada.lerTexto(String.format("Tentativa %d/%d: ",
                    tentativasUsadas + 1, nivel.getTentativas()));

            if (texto.equalsIgnoreCase("d")) {
                pedirDica(segredo);
                continue;
            }

            int palpite;
            try {
                palpite = Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número ou D para dica.");
                continue;
            }

            if (palpite < 1 || palpite > nivel.getLimite()) {
                System.out.printf("O número deve estar entre 1 e %d.%n", nivel.getLimite());
                continue;
            }

            tentativasUsadas++;
            ultimoPalpite = palpite;

            if (palpite == segredo) {
                System.out.printf("Acertou em %d tentativa(s)!%n", tentativasUsadas);
                return true;
            }
            System.out.println(mensagemDiferenca(palpite, segredo));
        }

        System.out.println("Suas tentativas acabaram. O número era " + segredo + ".");
        return false;
    }

    private String mensagemDiferenca(int palpite, int segredo) {
        int diferenca = Math.abs(palpite - segredo);
        String direcao = palpite < segredo ? "maior" : "menor";

        if (diferenca <= nivel.getLimite() / 10) {
            return "Muito perto! O número é " + direcao + ".";
        } else if (diferenca <= nivel.getLimite() / 4) {
            return "Está perto. O número é " + direcao + ".";
        }
        return "Está longe. O número é " + direcao + ".";
    }

    private void pedirDica(int segredo) {
        System.out.println("\n1) Paridade (par/ímpar) -" + CUSTO_PARIDADE + " pts");
        System.out.println("2) Intervalo (metade superior/inferior) -" + CUSTO_INTERVALO + " pts");
        System.out.println("3) Proximidade (quente/frio) -" + CUSTO_PROXIMIDADE + " pts");
        System.out.println("0) Cancelar");

        switch (entrada.lerInteiro("Dica: ", 0, 3)) {
            case 1 -> {
                custoDicas += CUSTO_PARIDADE;
                System.out.println("O número é " + (segredo % 2 == 0 ? "par." : "ímpar."));
            }
            case 2 -> {
                custoDicas += CUSTO_INTERVALO;
                int metade = nivel.getLimite() / 2;
                if (segredo <= metade) {
                    System.out.printf("O número está na metade inferior (1 a %d).%n", metade);
                } else {
                    System.out.printf("O número está na metade superior (%d a %d).%n",
                            metade + 1, nivel.getLimite());
                }
            }
            case 3 -> {
                if (ultimoPalpite == -1) {
                    System.out.println("Faça um palpite antes de pedir essa dica.");
                    return;
                }
                custoDicas += CUSTO_PROXIMIDADE;
                System.out.println("Seu último palpite está " + temperatura(ultimoPalpite, segredo) + ".");
            }
            default -> System.out.println("Dica cancelada.");
        }
        System.out.println();
    }

    private String temperatura(int palpite, int segredo) {
        int diferenca = Math.abs(palpite - segredo);
        if (diferenca <= nivel.getLimite() / 10) {
            return "QUENTE";
        } else if (diferenca <= nivel.getLimite() / 4) {
            return "MORNO";
        }
        return "FRIO";
    }
}
