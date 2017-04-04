package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class AttributeVisitor extends VoidVisitorAdapter{
		public List<Attribute> attributeList = new ArrayList<Attribute>();
		
		// Override the visit function
		public AttributeVisitor() {}
        @Override
        public void visit(VariableDeclarationExpr v, Object arg) {
        	 List <VariableDeclarator> myVars = v.getVars();
             for (VariableDeclarator vars: myVars){
            	 Attribute attribute = new Attribute();
            	 attribute.attributeName = vars.getId().getName();
            	 attribute.attributeName = vars.
            	 attributeList.add(attribute);
             }
             
        	
        }
    }