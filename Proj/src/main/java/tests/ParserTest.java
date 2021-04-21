package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitor;

import metrics.*;
import metrics.Class;

class ParserTest { //ANA: não sei testar esta classe, nem sei se é suposto, porque é um main

	static JavaParser jp1;
	static ArrayList<Class> classes = new ArrayList<Class>();
	
	static MethodParser method_parser = new MethodParser();
	
	static List<Statement> stmt_list = new ArrayList<Statement>();
	static Statement stmt1;
	
	
	static final String FILE_PATH = "src/main/java/metrics/Package.java";
	static VoidVisitor<Void> classvisitor = new ClassParser();
	 
	 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		
		jp1 = new JavaParser();
		jp1.main(null);
		
		
		
		stmt1.asWhileStmt();
		stmt_list.add(stmt1);
		
		/*CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		((ClassParser) classvisitor).setCu(cu);
		
		VoidVisitor<Void> methodvisitor = new MethodParser();
		((MethodParser) methodvisitor).setCu(((ClassParser) classvisitor).getCu());
		BlockStmt test = ((ClassParser) classvisitor).getCu().getBody().get();
		List<Statement> stmt = test.getStatements();
		loop(stmt);*/

	}
	
	@Test
	void javaParserTest() {
		assertNotNull(jp1);
	}

	@Test
	void methodParserTest() {
		method_parser.loop(stmt_list);
		assertEquals(1, method_parser.getCYCLO_method());
	}
}
