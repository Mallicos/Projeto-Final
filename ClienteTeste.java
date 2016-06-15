package br.unipe.mlpiii.sistemabancario.modelo;

import junit.framework.TestCase;

public class ClienteTeste extends TestCase{
	private Cliente cliente;

	protected void setUp() {

		cliente = new Cliente();
	}
	protected void tearDown() {
		cliente = null;
	}
	public void testBusca(){
		Object nome;
		cliente.busca();
		String busca = null;
		assertEquals(cliente.getVerificar(),busca);
	}
	public void testNome(){
		Cliente conta = null;
		cliente.busca();
		cliente.setNome(cliente);
		Object nome;
		assertNull(getName());
	}


}
