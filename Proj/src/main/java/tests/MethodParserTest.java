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
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalBlockStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;

import metrics.*;

class MethodParserTest { 
	
	static MethodParser method_parser = new MethodParser();
	
	static List<Statement> stmt_list = new ArrayList<Statement>();
	static Statement stmt1 = new WhileStmt();
	static Statement stmt2 = new ForStmt();

	
	static final String FILE_PATH = "src/main/java/metrics/Package.java";
	static VoidVisitor<Void> classvisitor = new ClassParser();
	 
	 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		stmt1.asWhileStmt();
		stmt_list.add(stmt1);
		
	
	}
	
	@Test
	void methodParserAsWhileStmtTest() {
		method_parser.loop(stmt_list);
		assertEquals(1, method_parser.getCYCLO_method());
	}
	
	@Test
	void methodParserAsWhileStmtExceptionTest() {
		
	}
	
	@Test
	void methodParserIsForStmtTest() {
		stmt2.asForStmt();
		stmt_list.add(stmt2);

		method_parser.loop(stmt_list);
		assertEquals(1, method_parser.getCYCLO_method());
	}
}
