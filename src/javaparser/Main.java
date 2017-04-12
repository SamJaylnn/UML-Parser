package javaparser;

import java.io.*;
import java.util.*; 

import net.sourceforge.plantuml.SourceStringReader;

public class Main {
	public static void main(String[] args) throws Exception {
		//args= new String[2];
		//Scanner reader = new Scanner(System.in);
		//args[0] = reader.next();
		//args[1] = reader.next();
		if (args.length == 2) {
			UMLparser parser = new UMLparser(args[0], args[1]);
			parser.analyze();	
		}
		/* test case 1 */
		/*for (int i = 1; i <= 5; i++) {
			args[0] = "uml_cases/uml-parser-test-" + Integer.toString(i);
			args[1] = "uml" + Integer.toString(i) + ".png";
			
			UMLparser parser = new UMLparser(args[0], args[1]);
			parser.analyze();
		}*/
	}
}