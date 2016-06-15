package br.unipe.mlpiii.sistemabancario.controller;

import br.unipe.mlpiii.sistemabancario.dao.ClienteDAO;
import br.unipe.mlpiii.sistemabancario.modelo.Cliente;
import br.unipe.mlpiii.sistemabancario.modelo.Conta;
import java.util.List;

public class ClienteController {
    
    ClienteDAO clienteDAO = new ClienteDAO();
    
    public int cadastrarCliente(Cliente cliente, String senha){
        
        int conta = clienteDAO.cadastrarCliente(cliente, senha);
        
        return conta;
    }
    
    public void deletarCliente(String cpf){
        
        clienteDAO.deletarCliente(cpf);
    }
    
    public List<String> buscarCliente(String cpf){
        
        List<String> cliente = clienteDAO.buscarCliente(cpf);
        
        return cliente;
    }
    
    public void atualizarCliente(Cliente cliente, String senha){
        
        clienteDAO.atualizarCliente(cliente, senha);
    }
    
    public Cliente login(String cpf, String senha){
        
        Cliente cliente = clienteDAO.login(cpf, senha);
        
        return cliente;
    }
    
    public Conta buscarConta(String cpf, String senha){
        
        Conta conta = clienteDAO.buscarConta(cpf, senha);
        
        return conta;
    }
    
    public List<String> historico(String cpf){
        
        List<String> historico = clienteDAO.historico(cpf);
        
        return historico;
    }
    
}
