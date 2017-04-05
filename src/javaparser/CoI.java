package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.TypeParameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;


public class CoI {
	public String coiName;
	public List<ClassOrInterfaceType> coiExtends;
	public List<ClassOrInterfaceType> coiImplements;
	public List<String> coiReferences;
	public List<TypeParameter> coiTypeParameter;
	public boolean coiIsInterface;
	public List<Method> methodList;
	public List<Constructor> constructorList;
	public List<Attribute> attributeList;
	
	// Constructor
	public CoI() {
		coiName = new String();
		coiExtends = new ArrayList<ClassOrInterfaceType>();
		coiImplements = new ArrayList<ClassOrInterfaceType>();
		coiReferences = new ArrayList<String>();
		coiTypeParameter = new ArrayList<TypeParameter>();
		coiIsInterface = false;
		methodList = new ArrayList<Method>();
		constructorList = new ArrayList<Constructor>();
		attributeList = new ArrayList<Attribute>();
	}
}