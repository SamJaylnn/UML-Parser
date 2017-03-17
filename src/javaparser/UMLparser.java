package javaparser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;


public class UMLparser {
	private String filepath;
	private String outputfile;
	
	public UMLparser(String filepath, String outputfile) {
		this.filepath = filepath;
		this.outputfile = outputfile;
	}
	
	public void analyze() throws IOException {
		InputStream in = new ByteArrayInputStream(filepath.getBytes(StandardCharsets.UTF_8));
		CompilationUnit cu = null;
		try {
			cu = JavaParser.parse(in);
			new MethodVisitor().visit(cu, null);
		} catch (ParseException x) {
			
		} finally {
			in.close();
		}
	}
}