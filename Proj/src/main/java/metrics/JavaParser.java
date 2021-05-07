package metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class JavaParser {

	private static final String FILE_PATH = "src/main/java/metrics/Class.java";

	public static void main(String[] args) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		VoidVisitor<Void> classvisitor = new ClassParser();
		((ClassParser) classvisitor).setCu(cu);
		classvisitor.visit(cu, null);
		Package package1 = new Package(cu.getPackageDeclaration().get().getNameAsString());
		for (int i = 0; i < ((ClassParser) classvisitor).getClasses().size(); i++) {
			package1.addClass(((ClassParser) classvisitor).getClasses().get(i));
		}
		System.out.print(package1.getName_Package());
		System.out.println("Classes " + package1.getClass_list());
	}
	
	public static List<metrics.Class> ParseFile(File file) throws FileNotFoundException{
		CompilationUnit cu = StaticJavaParser.parse(file);
		VoidVisitor<Void> classvisitor = new ClassParser();
		((ClassParser) classvisitor).setCu(cu);
		classvisitor.visit(cu, null);
		List<metrics.Class> r= ((ClassParser) classvisitor).getClasses();
		return r;
		
	}

}
