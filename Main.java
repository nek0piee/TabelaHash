package com.mycompany.tabelahash;

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

                    // Inserir os dados na tabela hash usando a função hash específica
                    for (int codigo : conjuntoDados) {
                        Registro registro = new Registro(codigo);
                        tabela.inserir(registro, funcaoHash);
                    }

                    // Realizar buscas e medir o tempo de busca, o número de comparações e o número de colisões
                    long tempoTotal = 0;
                    int totalComparacoes = 0;
                    int totalColisoes = 0;
                    int numBuscas = 5;

                    for (int i = 0; i < numBuscas; i++) {
                        //tabela.resetarContadores(); // resetar contadores antes de cada busca começar
                        int codigoBusca = conjuntoDados[random.nextInt(tamanhoConjunto)];
                        long inicio = System.nanoTime();
                        Registro resultado = tabela.buscar(codigoBusca, funcaoHash);
                        long fim = System.nanoTime();
                        long tempoBusca = fim - inicio;

                        tempoTotal += tempoBusca;
                        totalComparacoes += tabela.getNumComparacoes();
                        totalColisoes += tabela.getNumColisoes();
                        if (resultado != null) {
                            totalColisoes += resultado.getColisoes();
                        }

                        System.out.println("Tamanho da tabela: " + tamanho);
                        System.out.println("Função hash: " + funcaoHash);
                        System.out.println("Tamanho do conjunto de dados: " + tamanhoConjunto);
                        System.out.println("Código de busca: " + codigoBusca);
                        System.out.println("Tempo de busca: " + tempoBusca + " nanosegundos");
                        System.out.println("Número de comparações: " + tabela.getNumComparacoes());
                        System.out.println("Número de colisões: " + tabela.getNumColisoes());
                        System.out.println("----------------------------------------");
                    }

                    // Calcular média do tempo de busca, do número de comparações e do número de colisões
                    long mediaTempoBusca = tempoTotal / numBuscas;
                    int mediaComparacoes = totalComparacoes / numBuscas;
                    int mediaColisoes = totalColisoes / numBuscas;

                    System.out.println("Média do tempo de busca: " + mediaTempoBusca + " nanosegundos");
                    System.out.println("Média do número de comparações: " + mediaComparacoes);
                    System.out.println("Média do número de colisões: " + mediaColisoes);
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