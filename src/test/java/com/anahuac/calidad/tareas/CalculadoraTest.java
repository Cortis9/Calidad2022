package com.anahuac.calidad.tareas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class CalculadoraTest {

	private static Calculadora calculadora;
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		System.out.println("Este es el before");
		//inicializacion
		
	   calculadora = new Calculadora();
	}

	@After
	public void tearDown() throws Exception {
		
		System.out.println("Este es el after");
	}

	@Test
	public void SumaDosNumerosIgualesTest() {
		
		double resultadoEsperado = 4;
		
		
		calculadora.suma(2, 2);
		
		double resultadoEjercicio = calculadora.getUltimaResultado();
		
		//verificacion
		
		assertThat(resultadoEsperado,is(resultadoEjercicio));
		
				
		
	}
	
	@Test
	public void SumaDosNumerosDeDosDigitosTest() {
		
        double resultadoEsperado = 40;
		
		
		calculadora.suma(20, 20);
		
		double resultadoEjercicio = calculadora.getUltimaResultado();
		
		//verificacion
		
		assertThat(resultadoEsperado,is(resultadoEjercicio));
		
	}
	
	@Test
	public void SumaMaximoMasUnoTest() {
		
		  double resultadoEsperado = Double.MAX_VALUE;
			
			calculadora.suma(Double.MAX_VALUE, 1);
			
			double resultadoEjercicio = calculadora.getUltimaResultado();
			
			
			
			//verificacion
			
			assertThat(resultadoEsperado,is(resultadoEjercicio));
		
	}
	
	@Test
	public void SumaMasCeroTest() {
		
		 double resultadoEsperado = 5;
				
			
			calculadora.suma(5, 0);
			
			double resultadoEjercicio = calculadora.getUltimaResultado();
			
			//verificacion
			
			assertThat(resultadoEsperado,is(resultadoEjercicio));
		
		
	}
	
	@Test
	public void RestaCeroTest() {
		
		 double resultadoEsperado = 5;
				
			
			calculadora.resta(5, 0);
			
			double resultadoEjercicio = calculadora.getUltimaResultado();
			
			//verificacion
			
			assertThat(resultadoEsperado,is(resultadoEjercicio));
		
		
	}
	
	@Test
	public void RestaTest() {
		
		 double resultadoEsperado = 10;
				
			
			calculadora.resta(20, 10);
			
			double resultadoEjercicio = calculadora.getUltimaResultado();
			
			//verificacion
			
			assertThat(resultadoEsperado,is(resultadoEjercicio));
		
		
	}
	
	@Test
	public void DivisionEnteraTest() {
		
		 int resultadoEsperado = 2;
				
		 int resultadoEjercicio = calculadora.divisionEntera(10, 5);
			
			
			//verificacion
			
			assertThat(resultadoEsperado,is(resultadoEjercicio));
		
		//comentario
	}
	
	@Test(expected = ArithmeticException.class)
	public void DivisionEntreCeroTest() {
		
		 double resultadoEsperado = Double.POSITIVE_INFINITY;
				
			
			calculadora.divisionEntera(10, 0);
			
			double resultadoEjercicio = calculadora.getUltimaResultado();
			
			
			//verificacion
			
			assertThat(resultadoEsperado,is(resultadoEjercicio));
		
		
	}
	

}
