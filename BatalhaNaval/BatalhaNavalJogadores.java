import java.util.Scanner;

public class BatalhaNavalJogadores extends BatalhaNaval {
  String jogador1;
  String jogador2;

  public BatalhaNavalJogadores() {
    criaTabuleiros();
  }

  public void jogar() {
    posicionaNaviosTabuleiros();
    while (true) {
      darTiro(1);
      imprimir(1);
      darTiro(1);
      imprimir(1);
      if (checaFimDeJogo()) {
        System.out.println("\t\t-----Jogador 1 Ganhou!------");
        break;
      }
      darTiro(2);
      imprimir(2);
      darTiro(2);
      imprimir(2);
      if (checaFimDeJogo()) {
        System.out.println("\t\t-----Jogador 2 Ganhou!------");
        break;
      }
    }
  }

  public void posicionaNaviosTabuleiros() {
    System.out.println("\n\t-------Posicionamento de navios: jogador 1:  -------\n");
    posicionaNavios(getTabuleiro(1), 1);
    System.out.println("\nSeu tabuleiro ficou como segue\n");
    imprimir(1);
    System.out.println("\n\t-------Posicionamento de navios: jogador 2:-------\n");
    posicionaNavios(getTabuleiro(2), 2);
    System.out.println("\nSeu tabuleiro ficou como segue\n");
    imprimir(2);
  }

}