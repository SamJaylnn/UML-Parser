package javaparser;

import java.util.List;
import java.util.*;

import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;


/*	for reference:
 *	String	getName() 
	NameExpr	getNameExpr() 	
	List<BodyDeclaration>	getMembers() 
	List<ClassOrInterfaceType>	getExtends() 
	List<ClassOrInterfaceType>	getImplements() 
	List<TypeParameter>	getTypeParameters() 
	boolean	isInterface() 
*/
public class CoIVisitor extends VoidVisitorAdapter{
		public CoI coi = new CoI();
		
		// Override the visit function
		public CoIVisitor() {}
		@Override
		public void visit(ClassOrInterfaceDeclaration c, Object arg) {	
			
			coi.coiName = c.getName();
			coi.coiExtends  = c.getExtends();
			coi.coiImplements = c.getImplements();
			coi.coiTypeParameter = c.getTypeParameters();
			coi.coiIsInterface = c.isInterface();

			MethodVisitor methodVisitor = new MethodVisitor();
			methodVisitor.visit(c, null);
			coi.methodList = methodVisitor.methodList;
			//System.out.println(methodVisitor.methodList);
			//System.out.println("--------------------------------");
		}

	}