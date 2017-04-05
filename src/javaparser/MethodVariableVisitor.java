package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class MethodVariableVisitor extends VoidVisitorAdapter{
	public List<Attribute> attributeList = new ArrayList<Attribute>();
	
	// Override the visit function
	public MethodVariableVisitor() {}
    @Override
    public void visit(VariableDeclarationExpr cu, Object arg)
    {   
    	List <VariableDeclarator> myVars = cu.getVars();
        for (VariableDeclarator vars: myVars){
            System.out.println("Variable Name: "+vars.getData());
            }
    }
}