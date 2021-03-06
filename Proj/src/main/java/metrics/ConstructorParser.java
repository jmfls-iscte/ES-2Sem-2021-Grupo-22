package metrics;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * This method reads a java file to create a {@link Method}
 */
public class ConstructorParser extends VoidVisitorAdapter<Void> {

	private CompilationUnit cu;
	private int CYCLO_constructor = 0;
	private ArrayList<Method> methods = new ArrayList<Method>();

	/**
	 * This method reads a java file to create a {@link Method}
	 * 
	 * @param md  the CompilationUnit
	 * @param arg arguments
	 */
	@Override
	public void visit(ConstructorDeclaration md, Void arg) {
		super.visit(md, arg);

		String s = getNameString(md);
		Method method = new Method(s);
		CYCLO_constructor = 0;
		BlockStmt test = md.getBody();
		List<Statement> stmt = test.getStatements();
		loop(stmt);
		method.setLOC_method(test.getRange().get().getLineCount());
		method.setCYCLO_method(CYCLO_constructor);
		method.setBegin(md.getBegin().get().line);
		method.setEnd(md.getEnd().get().line);

		methods.add(method);
	}

	/**
	 * This method goes through the method in the java file to count the number of
	 * cycles
	 * 
	 * @param stmt a list of statements
	 */
	private String getNameString(ConstructorDeclaration md) {
		String s = md.getName().toString();
		if (md.getParameters().size() == 0)
			s += "()";
		else {
			s += "(";
			for (int h = 0; h < md.getParameters().size(); h++) {
				String aux = md.getParameter(h).toString();
				String[] auxsplit = aux.split(" ");
				s += auxsplit[0];
				if (h == (md.getParameters().size() - 1))
					s += ")";
				else
					s += ",";
			}
		}
		return s;
	}

	/**
	 * Goes through the method in the java file to count the number of
	 * cycles
	 * 
	 * @param stmt a list of statements
	 */
	public void loop(List<Statement> stmt) {
		for (int i = 0; i < stmt.size(); i++) {
			if (stmt.get(i).isWhileStmt()) {
				CYCLO_constructor++;
				Statement aux = stmt.get(i).asWhileStmt().getBody();
				List<Statement> stmt2 = new ArrayList<Statement>();
				try {
					stmt2 = ((BlockStmt) aux).getStatements();
				} catch (Exception e) {
					stmt2.add(aux);
				}
				loop(stmt2);
			}
			if (stmt.get(i).isForStmt()) {
				CYCLO_constructor++;
				Statement aux = stmt.get(i).asForStmt().getBody();
				List<Statement> stmt2 = new ArrayList<Statement>();
				try {
					stmt2 = ((BlockStmt) aux).getStatements();
				} catch (Exception e) {
					stmt2.add(aux);
				}
				loop(stmt2);
			}
			if (stmt.get(i).isForEachStmt()) {
				CYCLO_constructor++;
				Statement aux = stmt.get(i).asForEachStmt().getBody();
				List<Statement> stmt2 = new ArrayList<Statement>();
				try {
					stmt2 = ((BlockStmt) aux).getStatements();
				} catch (Exception e) {
					stmt2.add(aux);
				}
				loop(stmt2);
			}
			if (stmt.get(i).isSwitchStmt()) {
				List<SwitchEntry> aux = stmt.get(i).asSwitchStmt().getEntries();
				ArrayList<Statement> stmt2 = new ArrayList<Statement>();
				CYCLO_constructor += stmt.get(i).asSwitchStmt().getEntries().size();
				for (int j = 0; j < stmt.get(i).asSwitchStmt().getEntries().size(); j++) {
					for (int k = 0; k < aux.get(j).getStatements().size(); k++) {
						stmt2.add(aux.get(j).getStatement(k));
					}
				}
				loop(stmt2);
			}
			if (stmt.get(i).isIfStmt()) {
				CYCLO_constructor++;
				Statement aux = stmt.get(i).asIfStmt().getThenStmt();
				List<Statement> stmt2 = new ArrayList<Statement>();
				try {
					stmt2 = ((BlockStmt) aux).getStatements();
					if (stmt.get(i).asIfStmt().hasElseBlock()) {
						CYCLO_constructor++;
						Statement aux2 = stmt.get(i).asIfStmt().getElseStmt().get();
						aux2.asBlockStmt().getStatements();
						stmt2.addAll(aux2.asBlockStmt().getStatements());
					}
				} catch (Exception e) {
					stmt2.add(aux);
				}
				loop(stmt2);

			}
		}
	}

	/**
	 * Gets an ArrayList of methods in java file
	 * 
	 * @return an ArrayList of methods
	 */
	public ArrayList<Method> getMethods() {
		return methods;
	}

	/**
	 * Gets the CompilationUnit
	 * 
	 * @return the CompilationUnit
	 */
	public CompilationUnit getCu() {
		return cu;
	}
	
	/**
	 * Sets the CompilationUnit
	 * 
	 * @param cu the CompilationUnit
	 */
	public void setCu(CompilationUnit cu) {
		this.cu = cu;
	}

	/**
	 * Gets the number of cycles in the constructor
	 * 
	 * @return the number of cycles in the constructor
	 */
	public int getCYCLO_constructor() {
		return CYCLO_constructor;
	}

}