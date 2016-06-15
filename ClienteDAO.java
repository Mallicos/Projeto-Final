package br.unipe.mlpiii.sistemabancario.dao;

import br.unipe.mlpiii.sistemabancario.modelo.Cliente;
import br.unipe.mlpiii.sistemabancario.modelo.Conta;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=SistemaBancario";                        
    private final String user = "sa";
    private final String password = "32141409";
    
    //MÉTODO OK BIURRRRRRRRRRRRRRRRRRRR
    public int cadastrarCliente(Cliente cliente, String senha){
        
    	Random random = new Random();
        int numeroConta = random.nextInt(100000);
        String verificador = null;
        String verificador2 = cliente.getCpf();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            CallableStatement callable1 = connection.prepareCall("{CALL SP_VERIFICAR_CADASTRO(?)}");
            
            callable1.setString(1, cliente.getCpf());
            
            ResultSet resultSet = callable1.executeQuery();

            while(resultSet.next()){
               verificador = resultSet.getString("Cpf");
            }
            
            callable1.close();
            resultSet.close();

            
            if(verificador2.equals(verificador)){
                JOptionPane.showMessageDialog(null,"CPF inválido, tente novamente");
                
                connection.close();
                statement.close();
                
                return 0;
            }
            else{
                CallableStatement callable2 = connection.prepareCall("{CALL SP_ADD_CLIENTE(?,?,?,?,?,?,?,?,?,?,?,?)}");
            
                callable2.setString(1, cliente.getCpf());
                callable2.setString(2, cliente.getNome());
                callable2.setString(3, cliente.getDataNascimento());
                callable2.setString(4, cliente.getSexo());
                callable2.setString(5, cliente.getBairro());
                callable2.setString(6, cliente.getRua());
                callable2.setInt(7, cliente.getNumeroCasa());
                callable2.setString(8, cliente.getTelefone());
                callable2.setString(9, cliente.getEmail());
                callable2.setString(10, senha);
                callable2.setInt(11, numeroConta);
                callable2.setFloat(12, 0);
            
                callable2.execute();
            
                callable2.close(); 
                connection.close();
                statement.close();
                
                return numeroConta;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    //MÉTODO OK BIURRRRRRRRRRRRRRR
    public void deletarCliente(String cpf){
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            CallableStatement callable1 = connection.prepareCall("{CALL SP_VERIFICAR_CADASTRO(?)}");
            
            callable1.setString(1, cpf);
            
            ResultSet resultSet = callable1.executeQuery();
            
            String verificador = null;
            
            while(resultSet.next()){
            	verificador = resultSet.getString(1);
            }
            
            if(cpf.equals(verificador)){
            	
            	CallableStatement callable2 = connection.prepareCall("{CALL SP_DELETE_CLIENTE(?)}");
                
                callable2.setString(1, cpf);
                
                callable2.execute();

                callable2.close();
                
                JOptionPane.showMessageDialog(null,"Cliente deletado!");
            }
            else{
            	JOptionPane.showMessageDialog(null,"O cliente não existe!");
            }
            
            connection.close();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //MÉDOTO OK BIURRRRRRRRRRRR
    public List<String> buscarCliente(String cpf){
        
        List<String> dadosCliente = new ArrayList<>();
        String verificador = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            CallableStatement callable1 = connection.prepareCall("{CALL SP_VERIFICAR_CADASTRO(?)}");
            
            callable1.setString(1, cpf);
            
            ResultSet resultSet1 = callable1.executeQuery();
            
            while(resultSet1.next()){
            	verificador = resultSet1.getString(1);
            }
            
            callable1.close();
            resultSet1.close();
            
            if(cpf.equals(verificador)){
            	CallableStatement callable = connection.prepareCall("{CALL SP_GET_CLIENTE_E_CONTA(?)}");
                
                callable.setString(1, cpf);
                
                ResultSet resultSet2 = callable.executeQuery();
                
                while(resultSet2.next()){
                    
                    String cpfCliente = resultSet2.getString("Cpf");
                    
                    String nome = resultSet2.getString("Nome");
                    
                    String dataNascimento = resultSet2.getString("dataNascimento");
                    
                    String sexo = resultSet2.getString("Sexo");
                    
                    String bairro = resultSet2.getString("Bairro");
                    
                    String rua = resultSet2.getString("Rua");
                    
                    String numeroCasa = resultSet2.getString("NumeroCasa");
                    
                    String telefone = resultSet2.getString("Telefone");
                    
                    String email = resultSet2.getString("Email");
                    
                    String numeroConta = resultSet2.getString("NumeroConta");
                    
                    String saldo = resultSet2.getString("Saldo");
                    
                    dadosCliente.add(cpfCliente+"|"+nome+"|"+dataNascimento+"|"+sexo+"|"+bairro+"|"+"|"+rua+"|"+numeroCasa+"|"+telefone+
                                     "|"+email+"|"+numeroConta+"|"+saldo);
                }
                
                connection.close();
                statement.close();
                callable.close();
                resultSet2.close();
                
                return dadosCliente;	
            }
            
            else{
            	connection.close();
                statement.close();
            	JOptionPane.showMessageDialog(null,"Cliente inexistente!");
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
    //TESTAR
    public void atualizarCliente(Cliente cliente, String senha){
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            CallableStatement callable = connection.prepareCall("{CALL SP_UPDATE_CLIENTE(?,?,?,?,?,?,?,?,?,?)}");
            
            callable.setString(1, cliente.getCpf());
            callable.setString(2, cliente.getNome());
            callable.setString(3, cliente.getDataNascimento());
            callable.setString(4, cliente.getSexo());
            callable.setString(5, cliente.getBairro());
            callable.setString(6, cliente.getRua());
            callable.setInt(7, cliente.getNumeroCasa());
            callable.setString(8, cliente.getTelefone());
            callable.setString(9, cliente.getEmail());
            callable.setString(10, senha);
            
            callable.execute();
            
            connection.close();
            statement.close();
            callable.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Cliente login(String cpf, String senha){
        
        String cpfBanco = null;
        String senhaBanco = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            CallableStatement callable1 = connection.prepareCall("{call SP_LOGIN_CLIENTE(?,?)}");
            
            callable1.setString(1, senha);
            callable1.setString(2, cpf);
            
            ResultSet resultSet1 = callable1.executeQuery();
            
            while(resultSet1.next()){
                
            	senhaBanco = resultSet1.getString("Senha");
                cpfBanco = resultSet1.getString("Cpf");
            }
            
            callable1.close();
            resultSet1.close();
            
            if((cpf.equals(cpfBanco)) && (senha.equals(senhaBanco))){
            	
            	CallableStatement callable2 = connection.prepareCall("{CALL SP_GET_CLIENTE(?)}");
            	
            	callable2.setString(1, cpf);
            	
            	ResultSet resultSet2 = callable2.executeQuery();
            	
            	Cliente cliente = new Cliente();
            	
            	while(resultSet2.next()){

            		cliente.setCpf(resultSet2.getString(1));
            		cliente.setNome(resultSet2.getString(2));
            		cliente.setDataNascimento(resultSet2.getString(3));
            		cliente.setSexo(resultSet2.getString(4));
            		cliente.setBairro(resultSet2.getString(5));
            		cliente.setRua(resultSet2.getString(6));
            		cliente.setNumeroCasa(resultSet2.getInt(7));
            		cliente.setTelefone(resultSet2.getString(8));
            		cliente.setEmail(resultSet2.getString(9));
            		//pesquisar sobre reflexao java
            	}
                
               JOptionPane.showMessageDialog(null,"Bem vindo!");
            	
            	connection.close();
                statement.close();
                callable2.close();
                resultSet2.close();
            	
            	return cliente;
            }
            else{
            	
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
    
    public Conta buscarConta(String cpf, String senha){
    	
    	String cpfBanco = null;
        String senhaBanco = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            CallableStatement callable1 = connection.prepareCall("{call SP_LOGIN_CLIENTE(?,?)}");
            
            callable1.setString(1, senha);
            callable1.setString(2, cpf);
            
            ResultSet resultSet1 = callable1.executeQuery();
            
            while(resultSet1.next()){
                
            	senhaBanco = resultSet1.getString("Senha");
                cpfBanco = resultSet1.getString("Cpf");
            }
            
            callable1.close();
            resultSet1.close();
            
            if((cpf.equals(cpfBanco)) && (senha.equals(senhaBanco))){
            	
            	CallableStatement callable2 = connection.prepareCall("{CALL SP_GET_CONTA(?)}");
            	
            	callable2.setString(1, cpf);
            	
            	ResultSet resultSet2 = callable2.executeQuery();
            	
            	Conta conta = new Conta();
            	
            	while(resultSet2.next()){
            		
            		conta.setSenha(resultSet2.getString(1));
            		conta.setNumeroConta(resultSet2.getInt(2));
            		conta.setSaldo(resultSet2.getFloat(3));
            	}
            	
            	callable2.close();
            	resultSet2.close();
            	connection.close();
            	statement.close();
            	
            	return conta;
            }
            
            connection.close();
            statement.close();
            
            return null;
            
		} catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> historico(String cpf){
    	
    	List<String> historico = new ArrayList<>();
    	
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			CallableStatement callable = connection.prepareCall("{CALL SP_GET_HISTORICO(?)}");
			
			callable.setString(1, cpf);
			
			ResultSet resultSet = callable.executeQuery();
			
			while(resultSet.next()){
				
				String tipo = resultSet.getString("Tipo");
				//historico.add(tipo);
				
				String valor = Integer.toString(resultSet.getInt("Valor"));
				//historico.add(valor);
				
				String contaTransferencia = resultSet.getString("ContaTransferencia");
				//historico.add(contaTransferencia);
				
				String data = resultSet.getString("Data");
				historico.add(tipo+"                         "+valor+"                         "
                                        +contaTransferencia+"                         "+data);
                                
                                try {
                                
                                File arqUsers = new File("Dados.txt"); 
		                FileWriter fileWriter = new FileWriter(arqUsers, true);//caso queira deixar o arquivo como append deixar                                                                          true.
		                BufferedWriter buffer = new BufferedWriter(fileWriter); 
		                PrintWriter printWriter = new PrintWriter(buffer);
                                
                                printWriter.print(historico);
                            } catch (Exception e) {
                            }
                                
				
			}
			
			connection.close();
			statement.close();
			callable.close();
			resultSet.close();
			
    		return historico;
    		
		}catch (SQLException ex) {
            ex.printStackTrace();
            
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }	
    }
}
