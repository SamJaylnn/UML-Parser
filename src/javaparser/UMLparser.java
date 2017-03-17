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
	private String folderpath;
	private String outputfile;
	
	public UMLparser(String folderpath, String outputfile) {
		this.folderpath = folderpath;
		this.outputfile = outputfile;
	}
	
	public void analyze() throws Exception {
		File folder = new File(folderpath);
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isFile() && fileEntry.getName().endsWith(".java")) {
				//FileInputStream in = new FileInputStream(fileEntry);
				CompilationUnit cu = null;
				cu = JavaParser.parse(fileEntry);
				new CoIVisitor().visit(cu, null);
			}
		}
	}
}