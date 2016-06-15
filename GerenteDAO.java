package br.unipe.mlpiii.sistemabancario.dao;

import br.unipe.mlpiii.sistemabancario.modelo.Gerente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GerenteDAO {
    
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=SistemaBancario";                        
    private final String user = "sa";
    private final String password = "32141409";
    
    public boolean cadastarGerente(Gerente gerente){
        
        String cpfAux = null;
        String codigoAux = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            CallableStatement callable = connection.prepareCall("{CALL SP_VERIFICAR_GERENTE(?,?)}");
            
            callable.setString(1, gerente.getCpf());
            callable.setString(2, gerente.getCodigo());
            
            ResultSet resultSet = callable.executeQuery();
            
            while(resultSet.next()){
                cpfAux = resultSet.getString(1);
                codigoAux = resultSet.getString(2);
            }
            
            if((gerente.getCpf().equals(cpfAux)) && (gerente.getCodigo().equals(codigoAux))){

                connection.close();
                statement.close();
                
                return false;
            }
            
            else{
            CallableStatement callable2 = connection.prepareCall("{CALL SP_ADD_GERENTE(?,?)}");
            
            callable2.setString(1, gerente.getCpf());
            callable2.setString(2, gerente.getCodigo());
            
            callable2.execute();
            
            callable2.close();
            
            connection.close();
            statement.close();
            
            return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
    }
    
}
    
    public Gerente loginGerente(Gerente gerente){
        
        String cpfAux = null;
        String codigoAux = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            CallableStatement callable = connection.prepareCall("{CALL SP_VERIFICAR_GERENTE(?,?)}");
            
            callable.setString(1, gerente.getCpf());
            callable.setString(2, gerente.getCodigo());
            
            ResultSet resultSet = callable.executeQuery();
            
            while(resultSet.next()){
                cpfAux = resultSet.getString(1);
                codigoAux = resultSet.getString(2);
            }
            
            if((gerente.getCpf().equals(cpfAux)) && (gerente.getCodigo().equals(codigoAux))){
                JOptionPane.showMessageDialog(null,"Bem vindo!");
               
                connection.close();
                statement.close();
                
                return gerente;
            }
            
            else{
                JOptionPane.showMessageDialog(null,"O gerente não existe!");
                
                connection.close();
                statement.close();
                
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
    }
    }
    
    public List<String> buscarTodosClientes(){
        
        List<String> clientes = new ArrayList<>();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
	    Connection connection = DriverManager.getConnection(url, user, password);
	    Statement statement = connection.createStatement();
	    CallableStatement callable = connection.prepareCall("{CALL SP_GET_TODOS_CLIENTES}");
	    
            ResultSet resultSet = callable.executeQuery();
            
            while(resultSet.next()){
                
                String cpfCliente = resultSet.getString("Cpf");
                                  
                String nome = resultSet.getString("Nome");
                                      
                String dataNascimento = resultSet.getString("dataNascimento");                   
                    
                String sexo = resultSet.getString("Sexo");                  
                    
                String bairro = resultSet.getString("Bairro");
                                        
                String rua = resultSet.getString("Rua");
                                       
                String numeroCasa = resultSet.getString("NumeroCasa");                   
                    
                String telefone = resultSet.getString("Telefone");
                
                String email = resultSet.getString("Email");
                    
                String numeroConta = resultSet.getString("NumeroConta");

                String saldo = resultSet.getString("Saldo");
                
                clientes.add(cpfCliente+"|"+nome+"|"+dataNascimento+"|"+sexo+"|"+bairro+"|"+rua+"|"+numeroCasa+"|"+telefone+"|"+email+
                            "|"+numeroConta+"|"+saldo);   
            }
            
                connection.close();
                statement.close();
                callable.close();
                resultSet.close();
                
                return clientes;
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
    }
    }
}
