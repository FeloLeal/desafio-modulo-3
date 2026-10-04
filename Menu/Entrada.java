package Menu;
import java.util.Scanner;

public class Entrada {

    private final Scanner scanner = new Scanner(System.in);

    public String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public int lerInteiro(String mensagem, int minimo, int maximo) {
        while (true) {
            String texto = lerTexto(mensagem);
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.printf("Digite um número entre %d e %d.%n", minimo, maximo);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
    }
}
