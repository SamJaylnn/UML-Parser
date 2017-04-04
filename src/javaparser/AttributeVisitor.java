package javaparser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.type.Type;

class AttributeVisitor extends VoidVisitorAdapter{
		public List<Attribute> attributeList = new ArrayList<Attribute>();
		
		// Override the visit function
		public AttributeVisitor() {}
        @Override
        public void visit(FieldDeclaration v, Object arg) {
        	 Type type = v.getType();
        	 String modifier = ModifierSet.getAccessSpecifier(v.getModifiers()).toString();
             for (Node vars: v.getChildrenNodes()){
            	 if(vars.getClass() == VariableDeclarator.class){
	            	 Attribute attribute = new Attribute();
	            	 attribute.attributeName = ((VariableDeclarator)vars).getId().getName();
	            	 attribute.type = type;
	            	 attribute.modifier = modifier;
	            	 attributeList.add(attribute);
	            	 
	            	 /***** test *****/
	            	 System.out.println("name:" + attribute.attributeName);
	            	 System.out.println("type:" + attribute.type);
	            	 System.out.println("modifier:" + attribute.modifier);
	            	 System.out.println(" ");
            	 }
             }
        }
    }