package br.unipe.mlpiii.sistemabancario.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javax.swing.JOptionPane;

public class ContaDAO {
    
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=SistemaBancario";                        
    private final String user = "sa";
    private final String password = "32141409";
    
    //MÉTODO OK BIURRRRRRRRRRRRRRRRR
    public void creditar(int conta, String cpf, float quantia){

        if(quantia <= 0)
           JOptionPane.showMessageDialog(null,"Quantia inválida!");
        
        else{       	
           try {
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
               Connection connection = DriverManager.getConnection(url, user, password);
               Statement statement = connection.createStatement();
               CallableStatement callable1 = connection.prepareCall("{CALL SP_CREDITAR_CONTA(?,?)}");

               callable1.setInt(1, conta);
               callable1.setFloat(2, quantia);
               
               callable1.execute();
               
               callable1.close();
            
               CallableStatement callable2 = connection.prepareCall("{CALL SP_HISTORICO_CLIENTE(?,?,?,?,?)}");
            
               callable2.setString(1, cpf);
               callable2.setString(2, "Depósito");
               callable2.setFloat(3, quantia);
               callable2.setInt(4, conta);
               Date date = new Date(System.currentTimeMillis());
               callable2.setDate(5, date);

               callable2.execute();
            
               callable2.close();
               connection.close();
               statement.close();
            
               }
           catch (SQLException ex) {
               ex.printStackTrace();
           }
           catch(Exception e){
               e.printStackTrace();
           }	
          }
    }
    
    //MÉTODO OK BIURRRRRRRRRRRRRRR
    public void debitar(int conta, String cpf, float quantia){

        if(quantia <= 0)
           JOptionPane.showMessageDialog(null,"Quantia inválida!");
        
        else{
           try {
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
               Connection connection = DriverManager.getConnection(url, user, password);
               Statement statement = connection.createStatement();
               CallableStatement callable1 = connection.prepareCall("{CALL SP_GET_SALDO(?,?)}");
                   
                   callable1.setInt(1, conta);
                   callable1.setString(2, cpf);
                
                   ResultSet resultSet = callable1.executeQuery();
                
                   float verificaSaldo = 0;
                
                   while(resultSet.next()){
                	   verificaSaldo = resultSet.getFloat("Saldo");
                   }
                   
                   callable1.close();
                   resultSet.close();
                
                   if(quantia > verificaSaldo)
                	 JOptionPane.showMessageDialog(null,"Saldo insuficiente!");    
                
                   else{
                      CallableStatement callable2 = connection.prepareCall("{CALL SP_DEBITAR_CONTA(?,?)}");
                
                      callable2.setInt(1, conta);
                      callable2.setFloat(2, quantia);
                
                      callable2.execute();
                
                      callable2.close();
                
                      CallableStatement callable3 = connection.prepareCall("{CALL SP_HISTORICO_CLIENTE(?,?,?,?,?)}");
                
                      callable3.setString(1, cpf);
                      callable3.setString(2, "Saque");
                      callable3.setFloat(3, quantia);
                      callable3.setInt(4, conta);
                      Date date = new Date(System.currentTimeMillis());
                      callable3.setDate(5, date);
                
                      callable3.execute();
                
                      callable3.close();                     
                   }  
                   
                   connection.close();
                   statement.close();              
               }
               
              catch (SQLException ex) {
                  ex.printStackTrace();
              }
              catch(Exception e){
                  e.printStackTrace();
              }	
         }
    }
    //MÉTODO OK BIURRRRRRRRRRRRRRRRRRR
    public void transferencia(String cpf, int contaDebito, int contaCredito, float valor){
    	
    	if(valor <= 0)
    		JOptionPane.showMessageDialog(null,"Quantia inválida!");
    	
    	else{
    		try {
    			float saldo = 0;
    			
    			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    			Connection connection = DriverManager.getConnection(url, user, password);
    			Statement statement = connection.createStatement();
    			//VERIFICA SE O CLIENTE TEM A QUANTIA QUE ELE QUER TRANSFERIR
    			CallableStatement callable1 = connection.prepareCall("{CALL SP_GET_SALDO(?,?)}");
    			
    			callable1.setInt(1, contaDebito);
    			callable1.setString(2, cpf);
    			
    			ResultSet resultSet1 = callable1.executeQuery();
    			
    			while(resultSet1.next()){
    				saldo = resultSet1.getFloat("Saldo");
    			}
    			
    			callable1.close();
    			resultSet1.close();
    			
    			if(valor > saldo)
    				JOptionPane.showMessageDialog(null,"Saldo insuficiente!");
    			
    			else{
    				//VERIFICA SE A CONTA QUE RECEBERA A QUANTIA EXISTE
    				CallableStatement callable2 = connection.prepareCall("{CALL SP_GET_NUMERO_CONTA_CLIENTE(?)}");
    				
    				callable2.setInt(1, contaCredito);
    				
    				ResultSet resultSet2 = callable2.executeQuery();
    				
    				int verificaConta = 0;
    				
    				while(resultSet2.next()){
    					verificaConta = resultSet2.getInt(1);
    				}
    				
    				callable2.close();
    				resultSet2.close();
    				
    				if(verificaConta == contaCredito){
    					//DEBITA A QUANTIA DA CONTA DO USUÁRIO QUE ESTÁ TRANSFERINDO
    					CallableStatement callable3 = connection.prepareCall("{CALL SP_DEBITAR_CONTA(?,?)}");
    					
    					callable3.setInt(1, contaDebito);
    					callable3.setFloat(2, valor);
    					
    					callable3.execute();
    					
    					callable3.close();
    					//CREDITA NA CONTA DO USUÁRIO QUE IRÁ RECEBER A QUNTIA
    					CallableStatement callable4 = connection.prepareCall("{CALL SP_CREDITAR_CONTA(?,?)}");
    					
    					callable4.setInt(1, contaCredito);
    					callable4.setFloat(2, valor);
    					
    					callable4.execute();
    					
    					callable4.close();
    					
    					CallableStatement callable5 = connection.prepareCall("{CALL SP_HISTORICO_CLIENTE(?,?,?,?,?)}");
    					
    					callable5.setString(1, cpf);
    					callable5.setString(2, "Transferência");
    					callable5.setFloat(3, valor);
    					callable5.setInt(4, contaCredito);
    					Date date = new Date(System.currentTimeMillis());
    					callable5.setDate(5, date);
    					
    					callable5.execute();
    					
    					callable5.close();
    				}
    				
    				else
    			            JOptionPane.showMessageDialog(null,"Conta inválida!");
    				
    				connection.close();
    				statement.close();
    			}
				
			} catch (SQLException ex) {
                ex.printStackTrace();
            }
            catch(Exception e){
                e.printStackTrace();
            }		
    	}	
    }
}
