package com.anahuac.calidad.tareaUnitTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import com.anahuac.calidad.curdMOCK.DAOmysqlEstudiante;
import com.anahuac.calidad.curdMOCK.Estudiante;

public class CuentaTest {
	
	private cuenta aux;
	private cuenta c1;
	private cuenta c2;
	private cuenta c3;
	private cuenta c4;
	private AlertListener al;
	private HashMap<Integer, cuenta> DB = new HashMap<Integer, cuenta>();

	@Before
	public void setUp() throws Exception {
		
		aux = Mockito.mock(cuenta.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@ParameterizedTest
	@NullSource
	public void Creation() {
		
		c1 = new cuenta("cuenta1",200,al);
		c2 = new cuenta("cuenta2",120,al);
		c3 = new cuenta("cuenta3",300,al);
				
		int oldSize = DB.size();
		
		DB.put(1,c1);
		DB.put(2,c2);
		DB.put(3,c3);
		
		int newSize = DB.size();
		
		assertThat(oldSize+3,is(newSize));
        
		
	}
	
	@Test
	public void AlertTest() {
		
		c4 = new cuenta("cuenta4",80,al);
		
		DB.put(2,c4);
		
	    aux.debit(c4.balance);

		Mockito.verify(0);
		
		
	}
	
	@Test
	public void Answer() {
		
		
		Answer <Boolean> Almacenar = invocation -> {
			
			cuenta aux1 = (cuenta) invocation.getArguments() [0];

			aux.credit(200);
			
            return true;
		
	 };
	
	
	

	
	}
	
	private cuenta c = DB.get(0);
	
	Answer <Integer> Comision = invocation -> {
		
		cuenta aux2 = (cuenta) invocation.getArguments() [0];

		
		
        return DB.size();
        
       
	
    };
    


		
	

}
