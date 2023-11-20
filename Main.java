import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Tamanho do vetor da tabela hash
        int[] tamanhos = {1000000};

        // Variações da função hash
        String[] funcoesHash = {"restoDivisao", "multiplicacao", "dobramento"};

        // Conjuntos de dados com diferentes tamanhos
        int[] tamanhosConjuntos = {20000, 100000, 300000};

        // Gerar conjuntos de dados aleatórios com a mesma semente (seed)
        Random random = new Random(123);

        for (int tamanho : tamanhos) {
            for (String funcaoHash : funcoesHash) {
                for (int tamanhoConjunto : tamanhosConjuntos) {
                    // Criar tabela hash com o tamanho escolhido
                    TabelaHash tabela = new TabelaHash(tamanho);

                    // Gerar conjunto de dados aleatório
                    int[] conjuntoDados = gerarConjuntoDadosAleatorio(tamanhoConjunto, random);

                    // Inserir elementos na tabela hash
                    for (int codigo : conjuntoDados) {
                        Registro registro = new Registro(codigo);
                        tabela.inserir(registro);
                    }

                    // Realizar buscas e medir o tempo de busca e o número de comparações
                    long tempoTotal = 0;
                    int totalComparacoes = 0;
                    int numBuscas = 5;

                    for (int i = 0; i < numBuscas; i++) {
                        int codigoBusca = conjuntoDados[random.nextInt(tamanhoConjunto)];
                        long inicio = System.nanoTime();
                        Registro resultado = tabela.buscar(codigoBusca);
                        long fim = System.nanoTime();
                        long tempoBusca = fim - inicio;

                        tempoTotal += tempoBusca;
                        totalComparacoes += tabela.getNumComparacoes();

                        System.out.println("Tamanho da tabela: " + tamanho);
                        System.out.println("Função hash: " + funcaoHash);
                        System.out.println("Tamanho do conjunto de dados: " + tamanhoConjunto);
                        System.out.println("Código de busca: " + codigoBusca);
                        System.out.println("Tempo de busca: " + tempoBusca + " nanosegundos");
                        System.out.println("Número de comparações: " + tabela.getNumComparacoes());
                        System.out.println("----------------------------------------");
                    }

                    // Calcular média do tempo de busca e do número de comparações
                    long mediaTempoBusca = tempoTotal / numBuscas;
                    int mediaComparacoes = totalComparacoes / numBuscas;

                    System.out.println("Média do tempo de busca: " + mediaTempoBusca + " nanosegundos");
                    System.out.println("Número total de comparações: " + mediaComparacoes);
                    System.out.println("========================================");
                }
            }
        }
    }

    private static int[] gerarConjuntoDadosAleatorio(int tamanho, Random random) {
        int[] conjuntoDados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            conjuntoDados[i] = random.nextInt(1000000000);
        }
        return conjuntoDados;
    }
}