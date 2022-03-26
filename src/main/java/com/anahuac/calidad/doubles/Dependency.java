package com.anahuac.calidad.doubles;


public class Dependency {

	private final SubDependency subdependency;
	
	public Dependency(SubDependency subdependency) {
		
		super();
		
		this.subdependency = subdependency;
	}
	
	public String getClassName(){
		
		return this.getClass().getSimpleName();
		
	}
	
	public String getSubDependensyClassName(){
		
		return subdependency.getClassName();
		
	}
	
	public int addTwo(int i) {
		
		return 1 + 2;
	}
	
	public String getClassNameUpperCase() {
		
		return getClassName().toUpperCase();
	}
	
	
}
