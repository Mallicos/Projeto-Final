package br.unipe.mlpiii.sistemabancario.controller;

import br.unipe.mlpiii.sistemabancario.dao.ContaDAO;

public class ContaController {
    
    ContaDAO contaDAO = new ContaDAO();
    
    public void creditar(int conta, String cpf, float quantia){
        
        contaDAO.creditar(conta, cpf, quantia);
    }
    
    public void debitar(int conta, String cpf, float quantia){
        
        contaDAO.debitar(conta, cpf, quantia);
    }
    
    public void transferencia(String cpf, int contaDebito, int contaCredito, float valor){
        
        contaDAO.transferencia(cpf, contaDebito, contaCredito, valor);
    }
    
}
