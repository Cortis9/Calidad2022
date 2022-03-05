package com.anahuac.calidad.tareas;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


@RunWith(Parameterized.class)
public class SumaParametrisadaTest {
	
	private double suma1 = 0.0;
	private double suma2 = 0.0;
	private double resultado = 0.0;
	private Calculadora calculadora;
	
	public SumaParametrisadaTest(double sumando1,double sumando2,double resultado) {
		this.suma1 = sumando1;
		this.suma2 = sumando2;
		this.resultado = resultado;
	}
	
	@Parameters
	public static Collection<Object []> datos(){
		return Arrays.asList(new Object [][] {
		
			{2,2,4},
			{20,30,50},
			{20,0,20},
			{Double.MAX_VALUE,1,Double.MAX_VALUE}
			
		});
		
	}
	
	//comentario
	
	@Before
	public void setUp() throws Exception {
		
		calculadora = new Calculadora();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		double resulatdoEjecucion = calculadora.suma(suma1, suma2);
		
		assertThat(resulatdoEjecucion ,is(resultado));
	}

}
