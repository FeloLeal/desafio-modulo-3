package Menu;
import Historico.Historico;
import Historico.Recordes;
import Modelo.Nivel;
import Modelo.Pontuacao;
import Partida.Partida;
import Partida.PartidaClassica;
import Partida.PartidaSequencia;

public class Menu {

    private final Entrada entrada = new Entrada();
    private final Nivel[] niveis = Nivel.todos();
    private final Historico historico = new Historico();
    private final Recordes recordes = new Recordes(niveis);

    public void iniciar() {
        boolean executando = true;

        while (executando) {
            exibirOpcoes();
            switch (entrada.lerInteiro("Escolha: ", 1, 5)) {
                case 1 -> novoJogo();
                case 2 -> exibirRegras();
                case 3 -> historico.exibir();
                case 4 -> recordes.exibir();
                case 5 -> executando = false;
            }
        }

        System.out.println("Até a próxima!");
    }

    private void exibirOpcoes() {
        System.out.println("\n===== JOGO DE ADIVINHAÇÃO =====");
        System.out.println("1) Iniciar Novo Jogo");
        System.out.println("2) Ver Regras");
        System.out.println("3) Ver Histórico de Pontuações");
        System.out.println("4) Ver Recordes");
        System.out.println("5) Sair");
    }

    private void novoJogo() {
        Nivel nivel = escolherNivel();
        Partida partida = escolherModo(nivel);

        boolean venceu = partida.jogar();
        int pontos = partida.calcularPontuacao(venceu);

        System.out.println(venceu ? "Pontuação final: " + pontos + " pts" : "Você perdeu. Pontuação: 0");

        historico.adicionar(new Pontuacao(pontos, nivel.getNome(), partida.getModo(), venceu));
        if (venceu && recordes.registrar(nivel, pontos)) {
            System.out.println("NOVO RECORDE no nível " + nivel.getNome() + "!");
        }
    }

    private Nivel escolherNivel() {
        System.out.println("\nEscolha o nível:");
        for (int i = 0; i < niveis.length; i++) {
            System.out.printf("%d) %s%n", i + 1, niveis[i]);
        }
        return niveis[entrada.lerInteiro("Nível: ", 1, niveis.length) - 1];
    }

    private Partida escolherModo(Nivel nivel) {
        System.out.println("\nEscolha o modo:");
        System.out.println("1) Clássico (1 número, com dicas)");
        System.out.println("2) Sequência (3 números)");
        int modo = entrada.lerInteiro("Modo: ", 1, 2);
        return modo == 1 ? new PartidaClassica(nivel, entrada) : new PartidaSequencia(nivel, entrada);
    }

    private void exibirRegras() {
        System.out.println("\n===== REGRAS =====");
        System.out.println("- O computador sorteia um número (ou 3, no modo Sequência) e você tenta adivinhar.");
        System.out.println("- Fácil: 1 a 50, 10 tentativas | Médio: 1 a 100, 7 tentativas | Difícil: 1 a 200, 5 tentativas.");
        System.out.println("- Pontuação base: Fácil 100, Médio 200, Difícil 300.");
        System.out.println("- Cada tentativa usada desconta 10 pontos.");
        System.out.println("- Cada tentativa não utilizada vale +50 pontos de bônus.");
        System.out.println("- Dicas (modo Clássico, digite D): paridade -10, intervalo -20, proximidade -15.");
        System.out.println("- Perdeu a partida? A pontuação é 0.");
    }
}
