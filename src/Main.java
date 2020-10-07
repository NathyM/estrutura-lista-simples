import Model.*;

public class Main {

  public static void main(String[] args) {

    /*
     * Instanciando a classe genérica Vetor e nesse momento informando que ela é do
     * tipo Aluno. O construtor padrão cria um vetor com apenas 1 elemento que será
     * redimensionado se necessário.
     */
    Vetor<Aluno> alunos = new Vetor<>();

    // Instanciando alunos
    Aluno a1 = new Aluno("ANA", 30);
    Aluno a2 = new Aluno("MARIA", 35);
    Aluno a3 = new Aluno("CARLA", 20);
    Aluno a4 = new Aluno("JOSE", 20);
    Aluno a5 = new Aluno("VITOR", 24);
    Aluno a6 = new Aluno("PEDRO", 27);

    System.out.println("\n*** Teste dos métodos de adição (e método redimensiona) ***\n");

    System.out.println(alunos); // []
    alunos.adicionaFim(a1);
    alunos.adicionaFim(a2);
    alunos.adicionaFim(a3);
    System.out.println(alunos); // [ANA, MARIA, CARLA]
    alunos.adicionaInicio(a4);
    alunos.adicionaInicio(a5);
    System.out.println(alunos); // [VITOR, JOSE, ANA, MARIA, CARLA]
    alunos.adiciona(a6, 3);
    System.out.println(alunos); // [VITOR, JOSE, ANA, PEDRO, MARIA, CARLA]

    System.out.println("\n*** Teste do método existeDado ***\n");

    System.out.println(alunos.existeDado(3)); // true
    System.out.println(alunos.existeDado(7)); // A posição 7 não existe. A lista vai de 0 a 5. false

    System.out.println("\n*** Teste do método recupera ***\n");
    System.out.println(alunos.recupera(3)); // PEDRO
    System.out.println(alunos.recupera(10)); // ERRO! A posição 10 não existe. A lista vai de 0 a 5. null

    System.out.println("\n*** Teste dos métodos de remoção ***\n");
    System.out.println(alunos); // [VITOR, JOSE, ANA, PEDRO, MARIA, CARLA]
    alunos.removeFim();
    System.out.println(alunos); // [VITOR, JOSE, ANA, PEDRO, MARIA]
    alunos.removeInicio();
    System.out.println(alunos); // [JOSE, ANA, PEDRO, MARIA]
    alunos.remove(2);
    System.out.println(alunos); // [JOSE, ANA, MARIA]
    alunos.remove(10); // ERRO! A posição 10 não existe. A lista vai de 0 a 2.

    System.out.println("\n*** Teste do método tamanho ***\n");
    System.out.println(alunos.tamanho()); // 3

    System.out.println("\n*** Teste dos métodos vazio e limpa ***\n");
    System.out.println(alunos); // [JOSE, ANA, MARIA]
    System.out.println(alunos.vazio()); // false
    alunos.limpa();
    System.out.println(alunos); // []
    System.out.println(alunos.vazio()); // true

  }
}
