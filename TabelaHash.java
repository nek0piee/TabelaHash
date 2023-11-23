package com.mycompany.tabelahash;

// quando houver colisões, elas são tratadas por meio de encadeamento
// cada posição da tabela pode armazenar uma lista de registro
import java.util.Random;

public class TabelaHash {
    private int tamanho;
    private Registro[] tabela;
    private int numComparacoes;
    private int numColisoes;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Registro[tamanho];
        this.numComparacoes = 0;
        this.numColisoes = 0;
    }

    public void inserir(Registro registro, String funcaoHash) {
        int posicao;
        if (funcaoHash.equals("restoDivisao")) {
            posicao = calcularPosicaoRestoDivisao(registro.getCodigo());
        } else if (funcaoHash.equals("multiplicacao")) {
            posicao = calcularPosicaoMultiplicacao(registro.getCodigo());
        } else if (funcaoHash.equals("dobramento")) {
            posicao = calcularPosicaoDobramento(registro.getCodigo());
        } else {
            throw new IllegalArgumentException("Função de hash desconhecida");
        }

        if (tabela[posicao] == null) {
            tabela[posicao] = registro;
        } else {
            Registro atual = tabela[posicao];
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(registro);
            numColisoes++;
        }
    }

    public Registro buscar(int codigo, String funcaoHash) {
        int posicao;
        if (funcaoHash.equals("restoDivisao")) {
            posicao = calcularPosicaoRestoDivisao(codigo);
        } else if (funcaoHash.equals("multiplicacao")) {
            posicao = calcularPosicaoMultiplicacao(codigo);
        } else if (funcaoHash.equals("dobramento")) {
            posicao = calcularPosicaoDobramento(codigo);
        } else {
            throw new IllegalArgumentException("Função de hash desconhecida");
        }

        Registro atual = tabela[posicao];
        //numComparacoes = 0;
        while (atual != null) {
            numComparacoes++;
            if (atual.getCodigo() == codigo) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }
    private int calcularPosicaoRestoDivisao(int codigo) {
        return codigo % tamanho;
    }

    // Função hash de multiplicação
    private int calcularPosicaoMultiplicacao(int codigo) {
        double constante = 0.6180339887; // Constante recomendada para a função de multiplicação
        double valorHash = codigo * constante;
        return (int) (tamanho * (valorHash - Math.floor(valorHash)));
    }

    // Função hash de dobramento
    private int calcularPosicaoDobramento(int codigo) {
        int soma = 0;
        while (codigo > 0) {
            soma += codigo % 100; // Soma os dígitos do código em grupos de 2 dígitos
            codigo /= 100;
        }
        return soma % tamanho;
    }

    public int getNumComparacoes() {
        return numComparacoes;
    }

    public int getNumColisoes() {
        return numColisoes;
    }

}