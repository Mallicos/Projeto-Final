package br.unipe.mlpiii.sistemabancario.modelo;

public class Cliente {
    
    private String cpf;
    private String nome;
    private String dataNascimento;
    private String sexo;
    private String bairro;
    private String rua;
    private int numeroCasa;
    private String telefone;
    private String email;
    
    public Cliente(){ 
    }
    
    public Cliente(String cpf, String nome, String dataNascimento, String sexo, String bairro, String rua, int numeroCasa, String telefone, String email){
        
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.bairro = bairro;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.telefone = telefone;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }  
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }  
    
    @Override
    public String toString(){
        return this.cpf+"|"+this.nome+"|"+this.dataNascimento+"|"+this.sexo+"|"+this.bairro+"|"+this.rua+"|"+this.telefone+"|"+this.email;
    }
}
