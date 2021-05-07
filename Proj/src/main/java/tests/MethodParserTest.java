package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import metrics.*;

class MethodParserTest { 
	
	static MethodParser method_parser = new MethodParser();
	
	static List<Statement> stmt_list = new ArrayList<Statement>();
	static Statement stmt1 = new WhileStmt();
	static Statement stmt2 = new ForStmt();
	static Statement stmt3 = new SwitchStmt();
	static Statement stmt4 = new IfStmt();
	
	//erros
	static Statement stmt1_fail = new WhileStmt();

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		stmt1.asWhileStmt();
		stmt_list.add(stmt1);	
	}
	
	@Test
	void methodParserAsWhileStmtTest() {
		method_parser.loop(stmt_list);	
		assertNotEquals(0, method_parser.getCYCLO_method());
	}
	
	
	@Test
	void methodParserAsForStmtTest() {

		stmt2.asForStmt();
		stmt_list.add(stmt2);
		
		method_parser.loop(stmt_list);
		assertNotEquals(0, method_parser.getCYCLO_method());
	}
	
	@Test
	void methodParserAswitchStmtTest() {

		stmt3.asSwitchStmt();
		stmt_list.add(stmt3);

		method_parser.loop(stmt_list);
		assertNotEquals(0, method_parser.getCYCLO_method());
	}
	
	@Test
	void methodParserAsIfStmtTest() {

		stmt4.asIfStmt();
		stmt_list.add(stmt4);

		method_parser.loop(stmt_list);
		assertNotEquals(0, method_parser.getCYCLO_method());
	}
	
	@Test
	void getCuTest() { 
		assertNull(method_parser.getCu());	
	}
}
