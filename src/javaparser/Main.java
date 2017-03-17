package javaparser;



import java.io.IOException;
import java.util.Scanner; 

public class Main {
	public static void main(String[] args) throws Exception {
		args= new String[2];
		//Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a file path: ");
		//args[0] = reader.next();
		System.out.println("Enter a output file name: ");
		//args[1] = reader.next();
		
		/* test case 1 */
		args[0] = "uml_cases/uml-parser-test-4/ConcreteSubject.java";
		args[1] = "test.txt";
		
		UMLparser parser = new UMLparser(args[0], args[1]);
		parser.analyze();
		//System.out.println(args[0]);
		//System.out.println(args[1]);
	}
}