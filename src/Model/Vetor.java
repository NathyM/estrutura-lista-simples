package Model;

public class Vetor<T> {
  private T[] vetor;
  private int quantidadeElementos = 0;

  // Construtor padrão
  public Vetor() {
    vetor = (T[]) new Object[1];
  }

  // Construtor específico, passando o tamanho do vetor como parâmetro
  public Vetor(int tamanho) {
    vetor = (T[]) new Object[tamanho];
  }

  // Método que adiciona um elemento na posição indicada
  public void adiciona(T elemento, int posicao) {
    try {
      this.verificaIntervaloAdicao(posicao);
      this.redimensiona();

      Iterador<T> iterador = new Iterador<>(vetor);

      iterador.setPosicao(this.quantidadeElementos - 1);

      while (iterador.hasNext()) {
        this.vetor[iterador.getPosicao() + 1] = this.vetor[iterador.getPosicao()];
        if (iterador.getPosicao() < posicao) {
          break;
        }
        iterador.prev();
      }

      vetor[posicao] = elemento;
      quantidadeElementos++;
    } catch (IndexOutOfBoundsException e) {
      System.out.println("ERRO! A posição " + posicao + " não é válida. Escolha um valor de 0 a " + this.quantidadeElementos + ".");
    }
  }

  // Método para adicionar elemento no início do vetor
  public void adicionaInicio(T elemento) {
    this.redimensiona();

    Iterador<T> iterador = new Iterador<>(vetor);

    iterador.setPosicao(this.quantidadeElementos - 1);

    while (iterador.hasNext()) {
      this.vetor[iterador.getPosicao() + 1] = this.vetor[iterador.getPosicao()];
      iterador.prev();
    }

    vetor[0] = elemento;
    quantidadeElementos++;
  }

  // Método para adicionar um elemento no fim do vetor
  public void adicionaFim(T elemento) {
    this.redimensiona();

    vetor[this.quantidadeElementos] = elemento;
    quantidadeElementos++;
  }

  // Método para checar se existe dado em uma determinada posição
  public boolean existeDado(int posicao) {
    try {
      this.verificaIntervalo(posicao);
      return this.vetor[posicao] != null;
    } catch (IndexOutOfBoundsException e) {
      System.out.print("A posição " + posicao + " não existe. A lista vai de 0 a " + (this.quantidadeElementos - 1) + ". ");
      return false;
    } catch (NullPointerException e) {
      System.out.print("A posição " + posicao + " não existe. A lista está vazia. ");
      return false;
    }
  }

  // Método que retorna o dado na posição indicada
  public T recupera(int posicao) {
    try {
      this.verificaIntervalo(posicao);
      return this.vetor[posicao];
    } catch (IndexOutOfBoundsException e) {
      System.out.print("ERRO! A posição " + posicao + " não existe. A lista vai de 0 a " + (this.quantidadeElementos - 1) + ". ");
      return null;
    } catch (NullPointerException e) {
      System.out.print("ERRO! A posição " + posicao + " não existe. A lista está vazia. ");
      return null;
    }
  }

  // Método que verifica se o vetor está vazio
  public boolean vazio() {
    return this.quantidadeElementos == 0;
  }

  // Método que remove o item do vetor na posição passada como parâmetro
  public void remove(int posicao) {
    try {
      this.verificaIntervalo(posicao);
      Iterador<T> iterador = new Iterador<>(vetor);
      iterador.setPosicao(posicao);
      while (iterador.hasNext()) {
        this.vetor[iterador.getPosicao()] = this.vetor[iterador.getPosicao() + 1];
        iterador.next();
      }
      this.removeFim();
    } catch (IndexOutOfBoundsException e) {
      System.out.println("ERRO! A posição " + posicao + " não existe. A lista vai de 0 a " + (this.quantidadeElementos - 1) + ".");
    } catch (NullPointerException e) {
      System.out.println("ERRO! A posição " + posicao + " não existe. A lista está vazia.");
    }
  }

  // Método que remove a primeira posição do vetor
  public void removeInicio() {
    try {
      this.verificaIntervalo(0);
      Iterador<T> iterador = new Iterador<>(vetor);
      while (iterador.hasNext()) {
        this.vetor[iterador.getPosicao()] = this.vetor[iterador.getPosicao() + 1];
        iterador.next();
      }
      this.removeFim();
    } catch (NullPointerException e) {
      System.out.println("ERRO! A lista está vazia.");
    }
  }

  // Método que remove a ultima posição do vetor
  public void removeFim() {
    try {
      this.verificaIntervalo(this.quantidadeElementos - 1);
      this.vetor[this.quantidadeElementos - 1] = null;
      this.quantidadeElementos--;
    } catch (NullPointerException e) {
      System.out.println("ERRO! A lista está vazia.");
    }
  }

  // Método que retorna o tamanho do vetor
  public int tamanho() {
    return this.quantidadeElementos;
  }

  // Método que exclui todos os elementos do vetor
  public void limpa() {
    Iterador<T> iterador = new Iterador<>(vetor);
    while (iterador.hasNext()) {
      this.vetor[iterador.getPosicao()] = null;
      iterador.next();
    }

    this.quantidadeElementos = 0;
  }

  // Método que dobra o tamanho do vetor
  public void redimensiona() {
    if (this.quantidadeElementos == this.vetor.length) {

      T[] vetorCopia = (T[]) new Object[this.quantidadeElementos * 2];

      Iterador<T> iterador = new Iterador<>(vetor);
      while (iterador.hasNext()) {
        vetorCopia[iterador.getPosicao()] = this.recupera(iterador.getPosicao());
        iterador.next();
      }

      this.vetor = vetorCopia;
    }
  }

  // Imprime o vetor na tela, as posições vazias são incluidas para efeito de testes!
  @Override
  public String toString() {

    if (this.vazio()) return "[]";
    Iterador<T> iterador = new Iterador<>(vetor);
    String vetorToString = "[" + iterador.next();
    while (iterador.hasNext()) {
      vetorToString += ", " + iterador.next();
    }
    vetorToString += "]";

    return vetorToString;
  }

  // Retorna erro se a posição não estiver no intervalo de elementos existentes
  private void verificaIntervalo(int posicao) {
    if (this.vazio()) {
      throw new NullPointerException("Lista vazia");
    }
    if ((posicao < 0 || posicao >= this.quantidadeElementos)) {
      throw new ArrayIndexOutOfBoundsException("Posição Inválida");
    }
  }

  // Retorna erro se a posição não estiver no intervalo de elementos existentes ou no primeiro elemento vazio
  private void verificaIntervaloAdicao(int posicao) {
    if ((posicao < 0 || posicao > this.quantidadeElementos)) {
      throw new ArrayIndexOutOfBoundsException("Posição Inválida");
    }
  }

}
