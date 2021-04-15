package metrics;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class MethodParser extends VoidVisitorAdapter<Void> {

	private CompilationUnit cu;
	private int CYCLO_method = 0;
	private ArrayList<Method> methods = new ArrayList<Method>();

	@Override
	public void visit(MethodDeclaration md, Void arg) {
		super.visit(md, arg);
		Method method = new Method(md.getName().toString());
		CYCLO_method = 0;
		BlockStmt test = md.getBody().get();
		List<Statement> stmt = test.getStatements();
		loop(stmt);
		method.setLOC_method(test.getRange().get().getLineCount());
		method.setCYCLO_method(CYCLO_method);
		method.setBegin(md.getBegin().get().line);
		method.setEnd(md.getEnd().get().line);
		System.out.println("");
		System.out.println(method.getName_method());
		System.out.println("LOC_method " + method.getLOC_method());
		System.out.println("CYCLO_method " + method.getCYCLO_method());
		methods.add(method);
	}

	private void loop(List<Statement> stmt) throws java.lang.ClassCastException {
		for (int i = 0; i < stmt.size(); i++) {
			if (stmt.get(i).isWhileStmt()) {
				CYCLO_method++;
				Statement aux = stmt.get(i).asWhileStmt().getBody();
				List<Statement> stmt2 = ((BlockStmt) aux).getStatements();
				loop(stmt2);
			}
			if (stmt.get(i).isForStmt()) {
				CYCLO_method++;
				Statement aux = stmt.get(i).asForStmt().getBody();
				List<Statement> stmt2 = ((BlockStmt) aux).getStatements();
				loop(stmt2);
			}
			if (stmt.get(i).isForEachStmt()) {
				CYCLO_method++;
				Statement aux = stmt.get(i).asForEachStmt().getBody();
				List<Statement> stmt2 = ((BlockStmt) aux).getStatements();
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
//			
			if (stmt.get(i).isIfStmt()) {
				CYCLO_method++;
				Statement aux = stmt.get(i).asIfStmt().getThenStmt();
				System.out.print(aux);
				try {
					List<Statement> stmt2 = ((BlockStmt) aux).getStatements();
					if (stmt.get(i).asIfStmt().hasElseBlock()) {
						CYCLO_method++;
						Statement aux2 = stmt.get(i).asIfStmt().getElseStmt().get();
						aux2.asBlockStmt().getStatements();
						stmt2.addAll(aux2.asBlockStmt().getStatements());
					}
					loop(stmt2);
				} catch (Exception e) {
					System.out.print("");
				}
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

}
