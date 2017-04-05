package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.type.Type;

public class Attribute {
	public String attributeName;
	public String type;
	public String modifier;

	
	// Constructor
	public Attribute() {
		attributeName = new String();
		modifier = new String();
		type = new String();
	}
}
