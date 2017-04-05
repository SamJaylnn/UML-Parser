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
		public List<Attribute> attributeCollectionList = new ArrayList<Attribute>();
		
		// Override the visit function
		public AttributeVisitor() {}
        @Override
        public void visit(FieldDeclaration v, Object arg) {
        	 String type = v.getType().toString();
        	 //collection attribute or not collection attribute
        	 if (type.contains("<") && type.contains(">")) {
        		 type = type.substring(type.indexOf("<") + 1, type.lastIndexOf(">"));
            	 String modifier = ModifierSet.getAccessSpecifier(v.getModifiers()).toString();
                 for (Node vars: v.getChildrenNodes()){
                	 if(vars.getClass() == VariableDeclarator.class){
    	            	 Attribute attribute = new Attribute();
    	            	 attribute.attributeName = ((VariableDeclarator)vars).getId().getName();
    	            	 attribute.type = type;
    	            	 attribute.modifier = modifier;
    	            	 attributeCollectionList.add(attribute);
                	 }
                 }
        	 } else {
            	 String modifier = ModifierSet.getAccessSpecifier(v.getModifiers()).toString();
                 for (Node vars: v.getChildrenNodes()){
                	 if(vars.getClass() == VariableDeclarator.class){
    	            	 Attribute attribute = new Attribute();
    	            	 attribute.attributeName = ((VariableDeclarator)vars).getId().getName();
    	            	 attribute.type = type;
    	            	 attribute.modifier = modifier;
    	            	 attributeList.add(attribute);
                	 }
                 }
        	 }

        }
    }