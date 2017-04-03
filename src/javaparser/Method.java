package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.TypeParameter;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;

public class Method {
	public String methodName;
	public NameExpr methodNameExpr;
	public List<Parameter> methodParameters;
	public List<TypeParameter> methodTypeParameters;
	public List<ReferenceType> methodThrows;
	public Type methodType;
	public String modifier;
	
	// Constructor
	public Method() {
		methodName = new String();
		methodNameExpr = new NameExpr();
		methodParameters = new ArrayList<Parameter>();
		methodTypeParameters = new ArrayList<TypeParameter>();
		methodThrows = new ArrayList<ReferenceType>();	
		modifier = new String();
	}
}
