package javaparser;

import java.awt.List;
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
		public CoIVisitor() {}
		@Override
		public void visit(ClassOrInterfaceDeclaration c, Object arg) {	
			
			String getname = c.getName();
			NameExpr getnameexpr = c.getNameExpr();
			//ArrayList<BodyDeclaration> getmember = new ArrayList<BodyDeclaration>();
			//getmember = (ArrayList<BodyDeclaration>) c.getMembers();
			
			//System.out.println(getname);
			System.out.println(c);
			//System.out.println(getmember);
			System.out.println("--------------------------------");
			new MethodVisitor().visit(c, null);
		}

	}