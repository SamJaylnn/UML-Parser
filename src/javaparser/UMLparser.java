package javaparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.io.*;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import net.sourceforge.plantuml.SourceStringReader;


public class UMLparser {
	private String folderpath;
	private String outputfile;
	private List<CoI> coiList;
	private List<String> relationList;
    private StringBuilder classDiagram;
    private HashMap<String, CoI> coiMap;
	
    // Constructor
	public UMLparser(String folderpath, String outputfile) {
		this.folderpath = folderpath;
		this.outputfile = outputfile;
		coiList = new ArrayList<CoI>();
		relationList = new ArrayList<String>();
		classDiagram = new StringBuilder();
		coiMap = new HashMap<String, CoI>();
	}
	
	// Read all java files and use visitor to explore them
	public void analyze() throws Exception {
		File folder = new File(folderpath);
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isFile() && fileEntry.getName().endsWith(".java")) {
				CompilationUnit cu = JavaParser.parse(fileEntry);
				CoIVisitor coiVisitor = new CoIVisitor();
				coiVisitor.visit(cu, null);
				coiList.add(coiVisitor.coi);
				coiMap.put(coiVisitor.coi.coiName, coiVisitor.coi);
			}
		}
		plantUML(coiList);

		printUML();
	}
	
	// Construct the output string
	public void plantUML(List<CoI> coiList) {
		classDiagram.append("@startuml\n");
		plantUML_coi(coiList);
		plantUML_relation(coiList);
		classDiagram.append("\n@enduml");
	}
	
	
	// Construct the output string of class and interface
	public void plantUML_coi(List<CoI> coiList) {

		for (int i = 0; i < coiList.size(); i++) {
			CoI coi = coiList.get(i);
			if (coi.coiIsInterface) {
				classDiagram.append("interface ");
			} else {
				classDiagram.append("class ");
			}
			classDiagram.append(coi.coiName);
			classDiagram.append("\n");
			
			HashSet<String> attributeWithSetterAndGetter = new HashSet<String>();
			
			// add constructors
			for (int j = 0; j < coi.constructorList.size(); j++) {
				Constructor constructor = coi.constructorList.get(j);
				classDiagram.append(coi.coiName);
				classDiagram.append(" : ");
				classDiagram.append(getModifier(constructor.modifier));
				classDiagram.append(constructor.constructorName);
				classDiagram.append("(");
				
				// add constructors parameters
				for (int k = 0; k < constructor.constructorParameters.size(); k++) {
					StringBuilder strBuilder = new StringBuilder();
					strBuilder.append(constructor.constructorParameters.get(k));
					String str = strBuilder.toString();
					String[] strArray = str.split(" ");
					classDiagram.append(strArray[1] + " : " + strArray[0]);
					if (k < constructor.constructorParameters.size() - 1) {
						classDiagram.append(",");
					}
					
					//add relation ship
					if (coiMap.containsKey(strArray[0]) && coiMap.get(strArray[0]).coiIsInterface) {
						String relationStr = coi.coiName + " ..> " + strArray[0];
						relationList.add(relationStr);
					}
				}	
				classDiagram.append(")");
				classDiagram.append("\n");
			}
			
			
			// add methods
			for (int j = 0; j < coi.methodList.size(); j++) {
				Method method = coi.methodList.get(j);
				
				// ignore if it is not public methods
				if (!isPublic(method.modifier)) {
					continue;
				}
				
				// to find if it is a setter or a getter of a attribute
				if(method.methodName.toLowerCase().contains("set") 
						|| method.methodName.toLowerCase().contains("get")) {
					boolean found = false;
					for (int k = 0; k < coi.attributeList.size(); k++) {
						Attribute attribute = coi.attributeList.get(k);
						if (method.methodName.toLowerCase().contains(attribute.attributeName.toLowerCase())) {
							attributeWithSetterAndGetter.add(attribute.attributeName);
							found = true;
							break;
						}
					}
					if (found) {
						continue;
					}
				}
				
				classDiagram.append(coi.coiName);
				classDiagram.append(" : ");
				classDiagram.append(getModifier(method.modifier));
				classDiagram.append(method.methodName);
				classDiagram.append("(");
				
				// add method parameters
				for (int k = 0; k < method.methodParameters.size(); k++) {
					StringBuilder strBuilder = new StringBuilder();
					strBuilder.append(method.methodParameters.get(k));
					String str = strBuilder.toString();
					String[] strArray = str.split(" ");
					classDiagram.append(strArray[1] + " : " + strArray[0]);
					if (k < method.methodParameters.size() - 1) {
						classDiagram.append(",");
					}
				}
				classDiagram.append(")");
				classDiagram.append(" : ");
				classDiagram.append(method.methodType);
				classDiagram.append("\n");
			}
			
			
			// add attribute
			for (int j = 0; j < coi.attributeList.size(); j++) {
				Attribute attribute = coi.attributeList.get(j);
				
				//ignore if it is not private or public attribute
				if (!isPublic(attribute.modifier) && !isPrivate(attribute.modifier)) {
					continue;
				}
				
				//ignore if it has association with such class
				if (coiMap.containsKey(attribute.type) && !attribute.type.equals(coi.coiName)) {
					continue;
				}
				
				classDiagram.append(coi.coiName);
				classDiagram.append(" : ");
				if (attributeWithSetterAndGetter.contains(attribute.attributeName)) {
					classDiagram.append("+");
				} else {
					classDiagram.append(getModifier(attribute.modifier));
				}
				classDiagram.append(attribute.attributeName);
				classDiagram.append(" : ");
				classDiagram.append(attribute.type);
				classDiagram.append("\n");
			}
		}
	}
	
	// Construct the output string of relations
	public void plantUML_relation(List<CoI> coiList) {
		//associationMap records the which class has association with other classes
		HashMap<String, ArrayList<String>> associationMap1to1 = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> associationMap1toM = new HashMap<String, ArrayList<String>>();
		
		for (int i = 0; i < coiList.size(); i++) {
			CoI coi = coiList.get(i);
			
			associationMap1to1.put(coi.coiName, new ArrayList<String>());
			associationMap1toM.put(coi.coiName, new ArrayList<String>());
			
			// implement
			for (int j = 0; j < coi.coiImplements.size(); j++) {
				classDiagram.append(coi.coiName);
				classDiagram.append(" ..|> ");
				classDiagram.append(coi.coiImplements.get(j));
				classDiagram.append("\n");
			}
			
			// inheritance
			for (int j = 0; j < coi.coiExtends.size(); j++) {
				classDiagram.append(coi.coiName);
				classDiagram.append(" --|> ");
				classDiagram.append(coi.coiExtends.get(j));
				classDiagram.append("\n");
			}
			
			// dependency
			HashSet<String> dependencyset = new HashSet<String>();
			if (!coi.coiIsInterface) {
				for (int j = 0; j < coi.methodList.size(); j++) {
					Method tmp = coi.methodList.get(j);
					for (int k = 0; k < tmp.dependencyList.size(); k++) {
						String dependencyStr = tmp.dependencyList.get(k);
						if (coiMap.containsKey(dependencyStr) && coiMap.get(dependencyStr).coiIsInterface 
								&& !dependencyset.contains(dependencyStr)) {
							classDiagram.append(coi.coiName + " ..> " + dependencyStr);
							classDiagram.append("\n");
							dependencyset.add(dependencyStr);
						}
					}
				}
			}
			
			// Association (1 to many)
			for (int j = 0; j < coi.attributeCollectionList.size(); j++) {
				Attribute attribute = coi.attributeCollectionList.get(j);
				//add relation ship
				if (coiMap.containsKey(attribute.type)) {
					
					ArrayList<String> associationList = associationMap1toM.get(coi.coiName);
					if (!associationList.contains(attribute.type)){
						associationList.add(attribute.type);
					}
					associationMap1toM.put(coi.coiName, associationList);
				}
			}
			
			// Association (1 to 1)
			for (int j = 0; j < coi.attributeList.size(); j++) {
				Attribute attribute = coi.attributeList.get(j);
				//add relation ship
				if (coiMap.containsKey(attribute.type)) {
					ArrayList<String> associationList = associationMap1to1.get(coi.coiName);
					if (!associationList.contains(attribute.type)){
						associationList.add(attribute.type);
					}
					associationMap1to1.put(coi.coiName, associationList);
				}
			}
		}
		
		for (String type : associationMap1toM.keySet()) {
			ArrayList<String> associationList = associationMap1toM.get(type);
			for (int j = 0; j < associationList.size(); j++) {
				String otherType = associationList.get(j);
				ArrayList<String> tempList = associationMap1toM.get(otherType);
				// remove the duplicate association
				if (tempList.contains(type)) {
					classDiagram.append(type + " \"*\"--\"*\" " + otherType);
					classDiagram.append("\n");
					tempList.remove(type);
					associationMap1toM.put(otherType, tempList);
				} else {
					classDiagram.append(type + " --\"*\" " + otherType);
					classDiagram.append("\n");
				}
			}
		}
		
		for (String type : associationMap1to1.keySet()) {
			ArrayList<String> associationList = associationMap1to1.get(type);
			for (int j = 0; j < associationList.size(); j++) {
				String otherType = associationList.get(j);
				// remove the duplicate association
				if (associationMap1toM.get(type).contains(otherType) || associationMap1toM.get(otherType).contains(type)) {
					continue;
				} else {
					ArrayList<String> tempList = associationMap1to1.get(otherType);
					if (tempList.contains(type)) {
						tempList.remove(type);
						associationMap1to1.put(otherType, tempList);
					}
					classDiagram.append(type + " -- " + otherType);
					classDiagram.append("\n");
				}
			}
		}
		
		
		for (int i = 0; i < relationList.size(); i++) {
			classDiagram.append(relationList.get(i));
			classDiagram.append("\n");
		}
		
	}
	
	// Generate image
	public void printUML() throws Exception{
		SourceStringReader r = new SourceStringReader(classDiagram.toString());
		OutputStream png = new FileOutputStream(outputfile);
		r.generateImage(png);
	}
	
	public String getModifier(String modifier) {
		switch (modifier) {
		case "PUBLIC" : return "+";
		case "PROTECTED" : return "#";
		case "PRIVATE" : return "-";
		}
		return "";
	}
	
	public boolean isPrivate(String modifier) {
		return modifier.equals("PRIVATE");
	}
	
	public boolean isPublic(String modifier) {
		return modifier.equals("PUBLIC");
	}
}