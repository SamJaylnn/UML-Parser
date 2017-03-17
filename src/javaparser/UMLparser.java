package javaparser;

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


public class UMLparser {
	private String filepath;
	private String outputfile;
	
	public UMLparser(String filepath, String outputfile) {
		this.filepath = filepath;
		this.outputfile = outputfile;
	}
	
	public void analyze() throws Exception {
		FileInputStream in = new FileInputStream(filepath);
		CompilationUnit cu = null;

		cu = JavaParser.parse(in);
		List<Node> nodes = cu.getChildrenNodes();

		for (int i = 0; i < nodes.size(); i++) {
			System.out.println(i + " " + nodes.get(i));
		}
		
		
		new CoIVisitor().visit(cu, null);
		new MethodVisitor().visit(cu, null);
	}
}