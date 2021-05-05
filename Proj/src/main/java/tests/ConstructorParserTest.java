package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;

import metrics.ClassParser;
import metrics.ConstructorParser;

class ConstructorParserTest {
	
	static ConstructorParser constructor_parser = new ConstructorParser();
	
	static List<Statement> stmt_list = new ArrayList<Statement>();
	static Statement stmt1 = new WhileStmt();
	static Statement stmt2 = new ForStmt();
	static Statement stmt3 = new SwitchStmt();
	static Statement stmt4 = new IfStmt();
	static Statement stmt5 = new ForEachStmt();

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		stmt1.asWhileStmt();
		stmt_list.add(stmt1);
	}
	
	@Test
	void methodParserAsWhileStmtTest() {
		constructor_parser.loop(stmt_list);	
		assertNotEquals(0, constructor_parser.getCYCLO_constructor());
	}
	
	@Test
	void methodParserAsWhileStmtExceptionTest() { //FALTA FAZER
		
	}
	
	@Test
	void methodParserAsForStmtTest() {

		stmt2.asForStmt();
		stmt_list.add(stmt2);
		

		stmt3.asSwitchStmt();
		stmt_list.add(stmt3);

		
		constructor_parser.loop(stmt_list);
		assertNotEquals(0, constructor_parser.getCYCLO_constructor());
	}
	
	
	@Test
	void methodParserAsForStmtExceptionTest() { //FALTA FAZER
		
	}
	
	@Test
	void methodParserAswitchStmtTest() {

		stmt3.asSwitchStmt();
		stmt_list.add(stmt3);

		constructor_parser.loop(stmt_list);
		assertNotEquals(0, constructor_parser.getCYCLO_constructor());
	}
	
	@Test
	void methodParserAsSwitchForTest() { //FALTA FAZER
		
	}
	
	@Test
	void methodParserAsIfStmtTest() {

		stmt4.asIfStmt();
		stmt_list.add(stmt4);

		constructor_parser.loop(stmt_list);
		assertNotEquals(0, constructor_parser.getCYCLO_constructor());
	}
	
	@Test
	void methodParserAsIfStmtExceptionTest() { //FALTA FAZER
		
	}
	
	@Test
	void methodParserAsForEachStmtTest() {

		stmt5.asForEachStmt();
		stmt_list.add(stmt5);

		constructor_parser.loop(stmt_list);
		assertNotEquals(0, constructor_parser.getCYCLO_constructor());
	}
	
	@Test
	void methodParserAsForEachStmtExceptionTest() { //FALTA FAZER
		
	}
	
	@Test
	void getCuTest() { 
		assertNull(constructor_parser.getCu());	
	}
	

}
