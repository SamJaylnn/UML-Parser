package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class ConstructorVisitor extends VoidVisitorAdapter{
		public List<Constructor> constructorList = new ArrayList<Constructor>();
		
		// Override the visit function for visiting constructor of each class
		public ConstructorVisitor() {}
        @Override
        public void visit(ConstructorDeclaration c, Object arg) {
        	Constructor constructor = new Constructor();
        	constructor.constructorName = c.getName();
        	constructor.constructorParameters = c.getParameters();
        	constructor.modifier = ModifierSet.getAccessSpecifier(c.getModifiers()).toString();
        	constructorList.add(constructor);
        }
    }