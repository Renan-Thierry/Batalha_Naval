
public class Main {
  public static void main(String[] args) {
    int n[] = { 1, 2, 4, 7, 9, 0 };

    for (int i = 0; i < 10; i++) {
      System.out.println(n[i]);

      BatalhaNaval jogo = new BatalhaNaval();
      jogo.jogar();

    }
  }
}
