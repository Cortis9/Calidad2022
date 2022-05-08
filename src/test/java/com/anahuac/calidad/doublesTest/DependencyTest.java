package com.anahuac.calidad.doublesTest;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import com.anahuac.calidad.doubles.Dependency;

import org.junit.Before;
import org.junit.Test;

public class DependencyTest {
	
	 Dependency dependency;

	@Before
	public void setUp() throws Exception {
		
		dependency = Mockito.mock(Dependency.class);
	}

	@Test
	public void testDummy() {
		
		assertThat(dependency.getClassName(),is(nullValue()));
		assertThat(dependency.getClassNameUpperCase(),is(nullValue()));
		assertThat(dependency.getSubDependensyClassName(),is(nullValue()));
		
	}
	
	
	@Test
	public void classNametes() {
		
		String expected = "com.anahuac.calidad.doubles.Dependency";
		
		
		when(dependency.getClassName()).thenReturn("com.anahuac.calidad.doubles.Dependency");
		
		String result = dependency.getClassName();
		
		assertThat(result,is(expected));
		
	}
	
	@Test
	public void testDependency() {
		
		when(dependency.getClassName()).thenReturn("hi there");
		assertEquals("hi there",dependency.getClassName());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testException() {
		
		when(dependency.getClassName()).thenThrow(IllegalArgumentException.class);
		dependency.getClassName();
	}
	
	@Test
	public void testAddTow() {
		
		when(dependency.addTwo(10)).thenReturn(12);
		int expected = 12;
		
		int result = dependency.addTwo(10);
		
		assertThat(result,is(expected));
		
	}
	
	@Test
	public void testAddTow2() {
		
		when(dependency.addTwo(anyInt())).thenReturn(12);
		int expected = 12;
		
		int result = dependency.addTwo(10);
		
		assertThat(result,is(expected));
		
	}
	
	@Test
	public void testAnswer() {
		
	
		when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
		
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				
				int arg = (Integer) invocation.getArguments() [0];
				
				return arg + 2;
			}
			
		});
		
		assertThat(12,is(dependency.addTwo(10)));
		
	}
	
	@Test
	public void testAnswer2() {
		

			Answer <Integer> calcularSuma = invocation -> {
				
				int arg = (Integer) invocation.getArguments() [0];
				
                return arg+ 10;
			
		};
		
		when(dependency.addTwo(anyInt())).thenAnswer(calcularSuma);
		
		assertThat(20,is(dependency.addTwo(10)));
		
	}
	
	
	}


