package com.mycompany.tabelahash;

// cada um possui seu código de 9 dígitos que é usado pela função hash para determinar sua posição na tabela
public class Registro {
    private int codigo;
    private Registro proximo;
    private int colisoes;

    public Registro(int codigo) {
        this.codigo = codigo;
        this.proximo = null;
        this.colisoes = 0;
    }

    public int getCodigo() {
        return codigo;
    }

    public Registro getProximo() {
        return proximo;
    }

    public void setProximo(Registro proximo) {
        this.proximo = proximo;
    }

    public int getColisoes() {
        return colisoes;
    }

    public void incrementarColisoes() {
        colisoes++;
    }
}