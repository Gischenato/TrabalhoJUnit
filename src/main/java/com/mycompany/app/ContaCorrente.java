package com.mycompany.app;

public class ContaCorrente {
    private String numeroDaConta;
    private String correntista;
    private String categoria;
    private double saldo;

    public ContaCorrente(String numeroDaConta, String correntista) {
        this.correntista = correntista;
        this.numeroDaConta = numeroDaConta;
        this.saldo = 0;
    }

    public String getNumeroConta(){}
    public String getNomeCorrentista(){}
    public double getSaldo(){}
    public String getCategoria(){}
    public boolean deposito(double valor){}
    public boolean retirada(double valor){}
    
}
