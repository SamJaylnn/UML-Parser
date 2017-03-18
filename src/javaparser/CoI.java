package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.TypeParameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;


public class CoI {
	public String coiName;
	public List<ClassOrInterfaceType> coiExtends;
	public List<ClassOrInterfaceType> coiImplements;	
	public List<TypeParameter> getTypeParameter;
	public boolean coiIsInterface;
	
	public CoI() {
		coiName = new String();
		coiExtends = new ArrayList<ClassOrInterfaceType>();
		coiImplements = new ArrayList<ClassOrInterfaceType>();
		getTypeParameter = new ArrayList<TypeParameter>();
		coiIsInterface = false;
	}
}