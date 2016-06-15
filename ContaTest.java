package br.unipe.mlpiii.sistemabancario.modelo;


import junit.framework.TestCase;

public class ContaTest extends TestCase{

	private Conta conta;

	protected void setUp() {
		conta = new Conta(1000,0);
	}

	protected void tearDown() {
		conta = null;
	}

	public void testCreditar(){
		conta.creditar(1000.0);
		assertEquals(conta.getSaldo(), 1000.00);
	}

	public void testDebitar(){
		conta.creditar(1000);
		conta.debitar(500);
		assertEquals(conta.getSaldo(),500.00);
	}

	public void testTransferir(){
		Conta c = new Conta(0, 0);
		c.creditar(1000);//tenho 1000
		conta.creditar(100);//coloquei mais 100, ficou 1100
		c.transferir(conta,500);// transferi 500
		assertTrue(conta.getSaldo() == 600);//fiquei com 600

	}
}
