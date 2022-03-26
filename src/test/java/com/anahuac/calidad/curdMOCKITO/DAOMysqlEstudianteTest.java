package com.anahuac.calidad.curdMOCKITO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import com.anahuac.calidad.curdMOCK.DAOEstudianteMOCK;
import com.anahuac.calidad.curdMOCK.DAOmysqlEstudiante;
import com.anahuac.calidad.curdMOCK.Estudiante;

public class DAOMysqlEstudianteTest {

	private DAOmysqlEstudiante DAO;
	private Estudiante e;
	private ArrayList<Estudiante> DB = new ArrayList<Estudiante>();
	
	
	@Before
	public void setUp() throws Exception {
		DAO = Mockito.mock(DAOmysqlEstudiante.class);
	
	}

	@After
	public void tearDown() throws Exception {
		
		
	}

	@Test
	public void AddEstudianteTest() {
	
	
		Estudiante e = new Estudiante("nombre","apellido","correo","carrera");
		
		Answer <Integer> agregar = invocation -> {
			
			Estudiante newestudiante = (Estudiante) invocation.getArguments() [0];
			
			int oldSize = DB.size();
					
				e.setId(oldSize);
			
			DB.add(newestudiante);
			
            return DB.size();
		
	};
		
		
		when(DAO.createEstudiante(e)).thenAnswer(agregar);
		
		int oldsize = DB.size();
		
		assertThat(oldsize+1,is(DAO.createEstudiante(e)));
	
		
		
	}
	
	@Test
	
	public void DeletEstudiante() {
		
		Estudiante e = new Estudiante("nombre","apellido","correo","carrera");
		
		DB.add(e);
		
		e.setId(DB.size()-1);
		
		
        Answer <Boolean> eliminar = invocation -> {
			
		Integer aux = (Integer) invocation.getArguments() [0];
		
		
	    DB.remove(aux.intValue());
	    
		
		return true;
		
        };
		

		
       when(DAO.deleteEstudiante(e.getId())).thenAnswer(eliminar);
		
		int oldsize = DB.size();
		
		
		DAO.deleteEstudiante(e.getId());
		
		int newsize = DB.size();
		
		
		assertThat(oldsize-1,is(newsize));
		
		
	}
	
    @Test
	
	public void UpdateEmailEstudiante() {
		
    Answer <Boolean> actualizar = invocation -> {
			
    String aux = (String) invocation.getArguments() [0];
    
    int oldSize = DB.size();
		
	Estudiante e = DB.get(0);
	
	e.setEmail(aux);
	    
	System.out.println(DB);
	    
		
	return true;
		
    };
		
     when(DAO.updateEmailEstudiante(e)).thenAnswer(actualizar);
		
	 int oldsize = DB.size();
	 
	 DAO.updateEmailEstudiante(e);
		
	 int newsize = DB.size();
		
	 assertThat(oldsize,is(newsize));
		
		
	}

}
