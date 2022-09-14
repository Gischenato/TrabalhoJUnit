package com.mycompany.app;

public class ContaCorrente {
    private String numeroDaConta;
    private String correntista;
    private String categoria;
    private double saldo;

    public ContaCorrente(String numeroDaConta, String correntista) {
        this.correntista = correntista;
        this.numeroDaConta = numeroDaConta;
        this.categoria = "Silver";
        this.saldo = 0;
    }

    public String getNumeroConta(){
        return this.numeroDaConta;
    }
    public String getNomeCorrentista(){
        return this.correntista;
    }
    public double getSaldo(){
        return this.saldo;
    }
    public String getCategoria(){
        return this.categoria;
    }
    public boolean deposito(double valor){
        if(valor <= 0) return false;
        if(this.categoria.equals("Gold")) this.saldo += valor + valor*0.01;
        else if(this.categoria.equals("Platinum")) this.saldo += valor + valor*0.025;
        else this.saldo += valor;
        if(this.saldo >= 200000 && this.categoria.equals("Gold")) this.categoria = "Platinum";
        else if(this.saldo >= 50000 && this.categoria.equals("Silver")) this.categoria = "Gold";
        return true;
    }
    public boolean retirada(double valor){
        if(valor <= 0 || valor > this.saldo) return false;
        this.saldo -= valor;
        if(this.saldo < 25000 && this.categoria == "Gold") this.categoria = "Silver";
        else if(this.saldo < 100000 && this.categoria == "Platinum") this.categoria = "Gold";
        return true;
    }
    
}