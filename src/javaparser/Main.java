package javaparser;

import java.io.*;
import java.util.*; 

import net.sourceforge.plantuml.SourceStringReader;

public class Main {
	public static void main(String[] args) throws Exception {

		if (args.length == 2) {
			UMLparser parser = new UMLparser(args[0], args[1]);
			parser.analyze();	
		}

	}
}