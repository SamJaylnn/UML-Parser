package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class ConstructorVisitor extends VoidVisitorAdapter{
		public List<Constructor> constructorList = new ArrayList<Constructor>();
		
		// Override the visit function
		public ConstructorVisitor() {}
        @Override
        public void visit(ConstructorDeclaration m, Object arg) {
        	Constructor constructor = new Constructor();
        	constructor.constructorName = m.getName();
        	constructor.constructorParameters = m.getParameters();
        	constructorList.add(constructor);
        }
    }