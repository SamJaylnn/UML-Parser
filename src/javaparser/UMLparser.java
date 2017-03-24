package javaparser;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
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
    private StringBuilder classDiagram;
	
    // Constructor
	public UMLparser(String folderpath, String outputfile) {
		this.folderpath = folderpath;
		this.outputfile = outputfile;
		coiList = new ArrayList<CoI>();
		classDiagram = new StringBuilder();
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
			}
		}
		plantUML(coiList);
		printUML();
	}
	
	// 
	public void plantUML(List<CoI> coiList) {
		classDiagram.append("@startuml\n");
		for (int i = 0; i < coiList.size(); i++) {
			CoI coi = coiList.get(i);
			if (coi.coiIsInterface) {
				classDiagram.append("interface ");
			} else {
				classDiagram.append("class ");
			}
			classDiagram.append(coi.coiName);
			classDiagram.append("\n");
			for (int j = 0; j < coi.methodList.size(); j++) {
				Method method = coi.methodList.get(j);
				classDiagram.append(coi.coiName);
				classDiagram.append(" : ");
				classDiagram.append(method.methodName);
				classDiagram.append("\n");
			}
		}
		classDiagram.append("\n@enduml");
	}
	
	public void printUML() throws Exception{
		SourceStringReader r = new SourceStringReader(classDiagram.toString());
		OutputStream png = new FileOutputStream(outputfile);
		r.generateImage(png);
	}
	
	public void addDependence() {
		
	}
}