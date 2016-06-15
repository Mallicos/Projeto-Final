package br.unipe.mlpiii.sistemabancario.controller;

import br.unipe.mlpiii.sistemabancario.dao.ClienteDAO;
import br.unipe.mlpiii.sistemabancario.dao.GerenteDAO;
import br.unipe.mlpiii.sistemabancario.modelo.Gerente;
import java.util.ArrayList;
import java.util.List;

public class GerenteController {
    
   public boolean cadastarGerente(Gerente gerente){
       
       GerenteDAO gerenteDAO = new GerenteDAO();
       boolean cadastro = gerenteDAO.cadastarGerente(gerente);
       return cadastro;
   }
   
   public Gerente loginGerente(Gerente gerente){
       
       GerenteDAO gerenteDAO = new GerenteDAO();
       gerente = gerenteDAO.loginGerente(gerente);
       return gerente;
   }
   
   public List<String> buscarTodosClientes(){
       
       List<String> clientes = new ArrayList<>();
       GerenteDAO gerenteDAO = new GerenteDAO();
       clientes = gerenteDAO.buscarTodosClientes();
       
       return clientes;
   }
   
   public List<String> buscarCliente(String cpf){
       
       ClienteDAO clienteDAO = new ClienteDAO();
       List<String> clientes = clienteDAO.buscarCliente(cpf);
       
       return clientes;
   }
   
   public void deletarCliente(String cpf){
       
       ClienteDAO clienteDAO = new ClienteDAO();
       clienteDAO.deletarCliente(cpf);
   }
    
}
