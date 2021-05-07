package metrics;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodParser extends VoidVisitorAdapter<Void> {

	private CompilationUnit cu;
	private int CYCLO_method = 0;
	private ArrayList<Method> methods = new ArrayList<Method>();

	@Override
	public void visit(MethodDeclaration md, Void arg) {
		super.visit(md, arg);
		String s = getNameString(md);
		Method method = new Method(s);
		CYCLO_method = 0;
		BlockStmt test = md.getBody().get();
		List<Statement> stmt = test.getStatements();
		loop(stmt);
		method.setLOC_method(test.getRange().get().getLineCount());
		method.setCYCLO_method(CYCLO_method);
		method.setBegin(md.getBegin().get().line);
		method.setEnd(md.getEnd().get().line);

		methods.add(method);
	}

	private String getNameString(MethodDeclaration md) {
		String s = md.getName().toString();
		if (md.getParameters().size() == 0)
			s += "()";
		else {
			s += "(";
			int size = md.getParameters().size();
			for (int h = 0; h < size; h++) {
				String aux = md.getParameter(h).toString();
				String[] auxsplit = aux.split(" ");
				s += auxsplit[0];
				if (h == (size - 1))
					s += ")";
				else
					s += ",";
			}
		}
		return s;
	}

	public void loop(List<Statement> stmt){
		for (int i = 0; i < stmt.size(); i++) {
			if (stmt.get(i).isWhileStmt()) {
				CYCLO_method++;
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
				CYCLO_method++;
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
				CYCLO_method++;
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
				CYCLO_method += stmt.get(i).asSwitchStmt().getEntries().size();
				for (int j = 0; j < stmt.get(i).asSwitchStmt().getEntries().size(); j++) {
					for (int k = 0; k < aux.get(j).getStatements().size(); k++) {
						stmt2.add(aux.get(j).getStatement(k));
					}
				}
				loop(stmt2);
			}
			if (stmt.get(i).isIfStmt()) {
				CYCLO_method++;
				Statement aux = stmt.get(i).asIfStmt().getThenStmt();
				List<Statement> stmt2 = new ArrayList<Statement>();
				try {
					stmt2 = ((BlockStmt) aux).getStatements();
					if (stmt.get(i).asIfStmt().hasElseBlock()) {
						CYCLO_method++;
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

	public ArrayList<Method> getMethods() {
		return methods;
	}

	public CompilationUnit getCu() {
		return cu;
	}

	public void setCu(CompilationUnit cu) {
		this.cu = cu;
	}

	public int getCYCLO_method() {
		return CYCLO_method;
	}

}