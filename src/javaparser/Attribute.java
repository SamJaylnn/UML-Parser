package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.type.Type;

public class Attribute {
	public String attributeName;
	public Type type;
	public String modifier;

	
	// Constructor
	public Attribute() {
		attributeName = new String();
		modifier = new String();
	}
}
