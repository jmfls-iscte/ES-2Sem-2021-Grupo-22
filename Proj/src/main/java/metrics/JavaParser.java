package metrics;

import java.io.File;
import java.io.FileNotFoundException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class JavaParser {

	private static final String FILE_PATH = "src/main/java/excel/ExcelWrite.java";

	public static void main(String[] args) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		VoidVisitor<Void> classvisitor = new ClassParser();
		((ClassParser) classvisitor).setCu(cu);
		classvisitor.visit(cu, null);
	}

}
