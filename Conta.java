package br.unipe.mlpiii.sistemabancario.modelo;

public class Conta {
    
    private String senha;
    private int numeroConta;
    private float saldo;
    
    public Conta(){
    	
    }
    
    public Conta(String senha, int numeroConta, float saldo){
        
    	this.senha = senha;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }   
    
    @Override
    public String toString(){
    	return this.senha+"|"+this.numeroConta+"|"+this.saldo;
    }
}
