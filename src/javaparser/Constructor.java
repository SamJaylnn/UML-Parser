package javaparser;

import java.util.ArrayList;
import java.util.List;


import com.github.javaparser.ast.body.Parameter;

public class Constructor {
	public String constructorName;
	public List<Parameter> constructorParameters;

	
	// Constructor
	public Constructor() {
		constructorName = new String();
		constructorParameters = new ArrayList<Parameter>();
	}
}
