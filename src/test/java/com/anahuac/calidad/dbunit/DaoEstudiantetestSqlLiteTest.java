package com.anahuac.calidad.dbunit;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import com.anahuac.calidad.curdMOCK.*;

import junit.framework.TestCase;


public class DaoEstudiantetestSqlLiteTest extends TestCase{
	
	IDatabaseConnection connection;
	
	public DaoEstudiantetestSqlLiteTest(String name)
	{
		super(name);
	}

	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build(new File("src/resources/initDB.xml"));
	}
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		Connection jdbcConnection;
		
		jdbcConnection = DriverManager.getConnection("jdbc:mysql://uvbxs3hqonyjbgc2:qpIaIYHl1tMzpKj4vCgT@b65bd9j6rlmpmzkfzgkx-mysql.services.clever-cloud.com:3306/b65bd9j6rlmpmzkfzgkx");
		
		connection = new DatabaseConnection(jdbcConnection);
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = jdbcConnection.prepareStatement("Delete from Estudiante WHERE nombre = ?");
			// Set the values to match in the ? on query
			
			
			preparedStatement.setString(1, "Estudiante");
			
			Boolean result = false;

			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			preparedStatement.close();
			
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		} catch(Exception e) {
			fail("Error in setup: " + e.getMessage());
			connection.close();
		} 
	}
	
	@After
	public void tearDown()
	{
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public IDatabaseConnection getConnection()  {
		return connection;
	}

	@Test
	public void test() {
		Estudiante alumno = new Estudiante ("nombre1","apellido1","email" ,"carrera");
		DAOEstudianteSQLlite daoSQLite = new DAOEstudianteSQLlite ();
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		//verify
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("Estudiante");
			
			//Leer el archivo con el resultado esperado
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("Estudiante");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}
	}
	
	@Test
	public void testUpdate() {
	 
		DAOEstudianteSQLlite daoSQLite = new DAOEstudianteSQLlite ();
		
		Estudiante e2 = daoSQLite.findEstudiante(0);
		e2.setEmail("nuevo@mail.com");
		daoSQLite.updateEmailEstudiante(e2);
		
		//verify
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("Estudiante");
			
			//Leer el archivo con el resultado esperado
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/update_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("Estudiante");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}

	@Test
	public void testFind() {
	 
		DAOEstudianteSQLlite daoSQLite = new DAOEstudianteSQLlite ();
		
		Estudiante e2 = daoSQLite.findEstudiante(0);
		
		
		//verify
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("Estudiante");
			
			//Leer el archivo con el resultado esperado
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/find_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("Estudiante");
			
		  Integer aux1 = Integer.parseInt((String) expectedTable.getValue(0, "id"));
		  String aux2 = (String) expectedTable.getValue(0, "Nombre");
		  String aux3 = (String) expectedTable.getValue(0, "Apellido");
		  String aux4 = (String) expectedTable.getValue(0, "Email");
		  String aux5 = (String) expectedTable.getValue(0, "Carrera");
			
			assertThat(e2.getId(),is(aux1));
			assertThat(e2.getNombre(),is(aux2));
			assertThat(e2.getApellido(),is(aux3));
			assertThat(e2.getEmail(),is(aux4));
			assertThat(e2.getCarrera(),is(aux5));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	
	@Test
	public void testDelete() {
		
		DAOEstudianteSQLlite daoSQLite = new DAOEstudianteSQLlite ();
		
		Boolean e2 = daoSQLite.deleteEstudiante(0);
	
		
		//verify
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("Estudiante");
			
			//Leer el archivo con el resultado esperado
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/delete_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("Estudiante");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
}
