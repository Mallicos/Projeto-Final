package br.unipe.mlpiii.sistemabancario.modelo;

public class Gerente {
    
    private String cpf;
    private String codigo;
    
    public Gerente(String cpf, String codigo){
        this.cpf = cpf;
        this.codigo = codigo;
    }
    
    public Gerente(){
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
