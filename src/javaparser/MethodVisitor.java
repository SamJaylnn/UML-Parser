package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class MethodVisitor extends VoidVisitorAdapter{
		public List<Method> methodList = new ArrayList<Method>();
		
		// Override the visit function
		public MethodVisitor() {}
        @Override
        public void visit(MethodDeclaration m, Object arg) {
        	Method method = new Method();
        	method.methodName = m.getName();
        	method.methodNameExpr = m.getNameExpr();
        	method.methodParameters = m.getParameters();
        	method.methodThrows = m.getThrows();
        	method.methodType = m.getType().toString();
        	method.methodTypeParameters = m.getTypeParameters();
        	method.modifier = ModifierSet.getAccessSpecifier(m.getModifiers()).toString();
        	
        	MethodDependencyVisitor dependencyVisitor = new MethodDependencyVisitor();
        	dependencyVisitor.visit(m, null);
        	method.dependencyList = dependencyVisitor.dependencyList;
        	methodList.add(method);
        }
    }