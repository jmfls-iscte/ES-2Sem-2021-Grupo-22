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
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;

import metrics.*;
import metrics.Class;

class JavaParserTest { 
	
	static JavaParser jp1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception{		
		jp1 = new JavaParser();
		jp1.main(null);
	}
	
	@Test
	void javaParserTest() {
		assertNotNull(jp1);
	}
}
