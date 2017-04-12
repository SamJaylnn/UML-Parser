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

class MethodDependencyVisitor extends VoidVisitorAdapter{
	public List<String> dependencyList = new ArrayList<String>();
	
	// Override the visit function
	public MethodDependencyVisitor() {}
    @Override
    public void visit(MethodDeclaration m, Object arg)
    {           	
        for (Object node : m.getChildrenNodes()) {
        	 String[] bodyObjects = node.toString().split(" ");
             for (String bodyStr : bodyObjects) {
            	 dependencyList.add(bodyStr);
             }
        }
    }
}