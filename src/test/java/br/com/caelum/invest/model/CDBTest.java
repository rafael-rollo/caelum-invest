package br.com.caelum.invest.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

public class CDBTest {

	@Test
	public void deveRetornarRendimentoCorreto() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2);
		LocalDate dataProximoMes = LocalDate.now().plusMonths(1);
		
		CDB cdb = new CDB(new BigDecimal(3 * 12));
		
		BigDecimal valorReajustado = cdb.calculaRendimento(valorAplicado, dataProximoMes);
		
		assertEquals(new BigDecimal(309).setScale(2), valorReajustado);
	}
}
