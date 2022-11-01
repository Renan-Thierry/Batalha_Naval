public class Navio {
  private String tipoNavio;
  private Celula[] celulasOcupadas;
  private int celulasAtivas;
  private String simbolo;

  public Navio(String tipoNavio, int numeroCelulasOcupadas, String simbolo) {
      inicializaNavio(tipoNavio,numeroCelulasOcupadas, simbolo);
  }

  public void decrementaCelulasAtivas(){
      celulasAtivas--;
  }

  public void inicializaNavio(String tipoNavio, int numeroCeluasOcupadas, String simbolo) {
      this.tipoNavio=tipoNavio;
      celulasOcupadas=new Celula[numeroCeluasOcupadas];
      celulasAtivas=numeroCeluasOcupadas;
  this.simbolo=simbolo;
  }
  public boolean checaAfundado(){
  if(celulasAtivas==0)
          return true;
      return false;
  }
  
  public String getTipo(){
      return tipoNavio;
  }

  public void posicionaNavio(int linha, int coluna, char orientacao, Tabuleiro tabuleiro) throws Exception{
      int tamanho=tabuleiro.tamanhoTabuleiro();
  if(coluna>tamanho || linha>tamanho || linha<1 || coluna<1)
    throw new Exception ("Posicao fora do tabuleiro!");
  
  if(orientacao=='v'){
          if (linha+celulasOcupadas.length-1>tamanho){
              throw new Exception ("Posicao fora do tabuleiro!");
          }
    for(int i=0;i<celulasAtivas;i++){
              Celula celulaAux=tabuleiro.getCelula(linha+i-1,coluna-1);
              if (!celulaAux.getConteudo().equals("~~~")){
                  throw new Exception("Posicao ja esta ocupada!");                 
              }  
          }
          for(int i=0;i<celulasAtivas;i++){
              Celula celulaAux=tabuleiro.getCelula(linha+i-1,coluna-1);
              celulaAux.setConteudo(simbolo);
              celulasOcupadas[i]=celulaAux;
          }
      }
      else if(orientacao=='h'){
          if (coluna+celulasOcupadas.length-1>tamanho){
              throw new Exception ("Posicao fora do tabuleiro!");
          }
          for(int i=0;i<celulasAtivas;i++){
              Celula celulaAux=tabuleiro.getCelula(linha-1,coluna+i-1);
              if (!celulaAux.getConteudo().equals("~~~")){
                  throw new Exception("Posicao ja esta ocupada!");                 
              }
          }
          for(int i=0;i<celulasAtivas;i++){
              Celula celulaAux=tabuleiro.getCelula(linha-1,coluna+i-1);
              celulaAux.setConteudo(simbolo);
              celulasOcupadas[i]=celulaAux;
          }        
      }
  else
    throw new Exception("\nOrientacao invalida!\n");
  }

  public boolean checaCelulasOcupadas(Celula celula){
      for(int i=0;i<celulasOcupadas.length;i++)
          if(celulasOcupadas[i]==celula)
              return true;
      return false;
  }

public int getCelulasAtivas(){
  return celulasAtivas;
}

public int tamanhoNavio(){
  return celulasOcupadas.length;
}
}

