public class Tabuleiro {
	private Celula[][] tabuleiro;
	private Navio[] navios;
	private int naviosAtivos;

	public Tabuleiro(int tamanho) throws Exception {
		if (tamanho < 8 || tamanho > 8) {
			throw new Exception("\nTamanho padrão do tabuleiro é 8!\n");
		}
		if ((Object) tamanho == null)
			throw new Exception("Digite um inteiro");
		tabuleiro = new Celula[tamanho][tamanho];
		inicializaCelulas(tamanho);
		inicializaNavios();
	}

	public void inicializaCelulas(int tamanho) {
		for (int i = 0; i < tamanho; i++)
			for (int j = 0; j < tamanho; j++)
				tabuleiro[i][j] = new Celula();
	}

	public void inicializaNavios() {
		navios = new Navio[6];
		int i = 0;
		for (i = 0; i < 3; i++)
			navios[i] = new Submarino();
		for (i = 3; i < 5; i++)
			navios[i] = new Cruzador();
		for (i = 5; i < 6; i++)
		navios[i] = new PortaAviao();
		naviosAtivos = 6;
	}

	public void checaAfundou(Navio navio) {
		if (navio.checaAfundado()) {
			naviosAtivos--;
			System.out.println("Foi afundado um " + navio.getTipo() + "!");
		}
	}

	public Navio procuraAlvo(Celula celula) {
		int i;
		for (i = 0; i < navios.length; i++) {
			if (navios[i].checaCelulasOcupadas(celula))
				break;
		}
		return navios[i];
	}

	public void atiraNoNavio(Celula celula) {
		Navio navioAux = procuraAlvo(celula);
		navioAux.decrementaCelulasAtivas();
		checaAfundou(navioAux);
	}

	public Celula getCelula(int linha, int coluna) {
		return tabuleiro[linha][coluna];
	}

	public Navio[] getNavios() {
		return navios;
	}

	public int getNaviosAtivos() {
		return naviosAtivos;
	}

	public int tamanhoTabuleiro() {
		return tabuleiro.length;
	}

}