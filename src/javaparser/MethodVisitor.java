package javaparser;

import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class MethodVisitor extends VoidVisitorAdapter{
		public MethodVisitor() {}
        @Override
        public void visit(MethodDeclaration n, Object arg) {

        	System.out.println(n);
            System.out.println(n.getName());
        }
    }