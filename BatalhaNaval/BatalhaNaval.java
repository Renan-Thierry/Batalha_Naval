import java.util.Scanner;

public class BatalhaNaval implements Imprimivel {
  private Tabuleiro tabuleiro1;
  protected Tabuleiro tabuleiro2;
  private boolean fimDeJogo;

  public void jogar() {
    Scanner leitor = new Scanner(System.in);
    boolean pedeTipoJogo = true;
    while (pedeTipoJogo) {
      System.out.println("\n------------ BATALHA NAVAL ------------\n");
      System.out.println("\tClique em J para iniciar o jogo");
      System.out.println("\t[J] - Jogador1 vs Jogador2");
      System.out.println("-----------------------------------------");
      char tipoJogo = leitor.next().charAt(0);
      if (tipoJogo == 'j') {
        BatalhaNavalJogadores jogoJogadores = new BatalhaNavalJogadores();
        jogoJogadores.jogar();
        pedeTipoJogo = false;
      } else {
        System.out.println("\n------------ BATALHA NAVAL ------------\n");
        System.out.println("Opção invalida! Digite novamente");
        System.out.println("\n-----------------------------------------");
      }
    }
  }

  public void posicionaNavios(Tabuleiro tabuleiro, int numTabuleiro) {
    boolean continuaExcecao;
    System.out.println("\n------------ BATALHA NAVAL ------------\n");
    System.out.println("Digite 1 à 8 para linha, e digite de 1 à 8 para coluna. (h para horizontal e v para vertical)");
    System.out.println("\n-----------------------------------------");
    int linha;
    int coluna;
    char orientacao;
    Navio[] naviosAuxiliar = tabuleiro.getNavios();
    for (int i = 0; i < naviosAuxiliar.length; i++) {
      continuaExcecao = true;

      while (continuaExcecao) {
        try {
          System.out.println("\n------------ BATALHA NAVAL ------------\n");
          System.out.println("Posicione um " + naviosAuxiliar[i].getTipo() + ", que tem tamanho "
              + naviosAuxiliar[i].tamanhoNavio() + "\n");
          Scanner leitor = new Scanner(System.in);
          try {
            linha = leitor.nextInt();
            coluna = leitor.nextInt();
          } catch (Exception e) {
            System.out.println("\n------------ BATALHA NAVAL ------------\n");
            System.out.println("\nDigite numeros para a posicao!\n");
            continue;
          }
          orientacao = leitor.next().charAt(0);
          naviosAuxiliar[i].posicionaNavio(linha, coluna, orientacao, tabuleiro);
          continuaExcecao = false;
          imprimir(numTabuleiro);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  public void criaTabuleiros() {
    boolean continuaExcecao = true;
    int tamanho;
    while (continuaExcecao) {
      try {
        System.out.println("\n------------ BATALHA NAVAL ------------\n");
        System.out.print("\nDigite 8 para o padrão do tamanho do tabuleiro: ");
        Scanner leitor = new Scanner(System.in);
        try {
          tamanho = leitor.nextInt();
        } catch (Exception e) {
          System.out.println("\n------------ BATALHA NAVAL ------------\n");
          System.out.println("\nDigite numeros!");
          continue;
        }
        setTabuleiro(1, new Tabuleiro(tamanho));
        setTabuleiro(2, new Tabuleiro(tamanho));
        setFimDeJogo(false);
        continuaExcecao = false;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public Tabuleiro escolherTabuleiro(int tabuleiro) {
    if (tabuleiro == 1)
      return tabuleiro1;
    return tabuleiro2;
  }

  public void darTiroNaCelula(int linha, int coluna, Tabuleiro tabuleiro) throws Exception {
    Celula celula = tabuleiro.getCelula(linha - 1, coluna - 1);
    if (celula.getTiro())
      throw new Exception("\nJa foi dado tiro nessa celula!\n");
    celula.setTiro();
    if (celula.getConteudo().equals(" X "))
      tabuleiro.atiraNoNavio(celula);
  }

  public void darTiro(int linha, int coluna, int tabuleiro) throws Exception {
    Tabuleiro tabuleiroAuxiliar = escolherTabuleiro(tabuleiro);
    if (coluna > tabuleiroAuxiliar.tamanhoTabuleiro() || linha > tabuleiroAuxiliar.tamanhoTabuleiro() || linha < 1
        || coluna < 1) {
      throw new Exception("Tiro fora do tabuleiro!");
    }
    darTiroNaCelula(linha, coluna, tabuleiroAuxiliar);
    checaNumeroNavios(tabuleiro);
  }

  public void checaNumeroNavios(int tabuleiro) {
    Tabuleiro tabuleiroAuxiliar;
    if (tabuleiro == 1)
      tabuleiroAuxiliar = tabuleiro1;
    else
      tabuleiroAuxiliar = tabuleiro2;
    if (tabuleiroAuxiliar.getNaviosAtivos() == 0) {
      setFimDeJogo(true);
    }
  }

  public boolean checaFimDeJogo() {
    if (fimDeJogo)
      return true;
    return false;
  }

  public Tabuleiro getTabuleiro(int tabuleiro) {
    if (tabuleiro == 1)
      return tabuleiro1;
    return tabuleiro2;
  }

  public int[] pedeCoordenadas(int jogador) {
    boolean pede = true;
    int coordenadas[] = new int[2];
    System.out.println("\n------------ BATALHA NAVAL ------------\n");
    System.out.println("\n\t\t----Vez do Jogador " + jogador + "----\n");
    while (pede) {
      try {
        Scanner leitor = new Scanner(System.in);
        System.out.println("\n------------ BATALHA NAVAL ------------\n");
        System.out.println("Onde quer dar o tiro? ");
        coordenadas[0] = leitor.nextInt();
        coordenadas[1] = leitor.nextInt();
        pede = false;
      } catch (Exception e) {
        System.out.println("\n------------ BATALHA NAVAL ------------\n");
        System.out.println("\nDigite numeros\n");
      }
    }
    return coordenadas;
  }

  public void darTiro(int jogador) {
    boolean continuaAtirando = true;
    int[] coordenadas = new int[2];
    while (continuaAtirando) {
      try {
        coordenadas = pedeCoordenadas(jogador);
        if (jogador == 1)
          darTiro(coordenadas[0], coordenadas[1], 2);
        else
          darTiro(coordenadas[0], coordenadas[1], 1);
        continuaAtirando = false;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  @Override
  public void imprimirAuxiliar() {
    Tabuleiro tabuleiroAuxiliar = getTabuleiro(1);
    System.out.print("  ");
    for (int j = 0; j < tabuleiroAuxiliar.tamanhoTabuleiro(); j++)
      System.out.print("|---");
    System.out.print("|");
  }

  @Override
  public void imprimir() {
    imprimirAuxiliar();
    System.out.print("  ");
    imprimirAuxiliar();
  }

  public void imprimir(int tabuleiro) {
    System.out.println("\n------------ BATALHA NAVAL ------------\n");
    System.out.println("\t\t----------Tabuleiro " + tabuleiro + "----------\n");
    if (tabuleiro == 1) {
      imprimirTabuleiro(tabuleiro1, tabuleiro2);
      System.out.println();
    } else {
      imprimirTabuleiro(tabuleiro2, tabuleiro1);
      System.out.println();
    }
  }

  public void imprimirTabuleiro(Tabuleiro tabuleiro, Tabuleiro tabuleiroAuxiliar) {
    indicesSuperiores(tabuleiro.tamanhoTabuleiro());
    for (int i = 0; i < tabuleiro.tamanhoTabuleiro(); i++) {
      imprimir();
      System.out.println();
      System.out.print((i + 1) + " ");
      for (int j = 0; j < tabuleiro.tamanhoTabuleiro(); j++) {
        System.out.print("|");
        tabuleiro.getCelula(i, j).imprimir();
      }
      System.out.print("|  ");
      System.out.print("  ");
      for (int j = 0; j < tabuleiroAuxiliar.tamanhoTabuleiro(); j++) {
        System.out.print("|");
        tabuleiroAuxiliar.getCelula(i, j).imprimirAuxiliar();
      }
      System.out.println("|");
    }
    imprimir();
    System.out.println();
  }

  public void indicesSuperiores(int indiceMax) {
    System.out.print("  ");
    indiceSuperior(indiceMax);
    System.out.print("     ");
    indiceSuperior(indiceMax);
    System.out.println();
  }

  public void indiceSuperior(int indiceMax) {
    for (int i = 1; i <= indiceMax; i++)
      System.out.print("  " + i + " ");
  }

  public void setTabuleiro(int numTabuleiro, Tabuleiro tabuleiro) {
    if (numTabuleiro == 1)
      tabuleiro1 = tabuleiro;
    else
      tabuleiro2 = tabuleiro;
  }

  public void setFimDeJogo(boolean fimDeJogo) {
    this.fimDeJogo = fimDeJogo;
  }

}
