package metrics;

import java.util.ArrayList;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassParser extends VoidVisitorAdapter<Void> {

	private CompilationUnit cu;
	private ArrayList<Class> classes = new ArrayList<Class>();

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Void arg) {
		super.visit(n, arg);
		Package package1 = new Package(cu.getPackageDeclaration().get().getNameAsString());
		VoidVisitor<Void> methodvisitor = new MethodParser();
		((MethodParser) methodvisitor).setCu(getCu());
		methodvisitor.visit(cu, null);
		Class class1 = new Class(n.getName().toString());
		class1.setBegin(n.getBegin().get().line);
		class1.setEnd(n.getEnd().get().line);
		class1.setLOC_class(n.getRange().get().getLineCount());
//		class1.setPacage(this.getP());
		classmethod(class1,(MethodParser)methodvisitor);
		package1.addClass(class1);
		System.out.println("");
//		System.out.println("Package " + class1.getPacage());
		System.out.println("LOC_class " + class1.getLOC_class());
		System.out.println("NOM_class " + class1.getNOM_class());
		System.out.println("WMC_class " + class1.getWMC_class());
		System.out.println("CLASSSES "+ package1.getClass_list());
	}

	private void classmethod(Class class2, MethodParser m) {
		for (int i = 0; i < m.getMethods().size(); i++) {
			if ((class2.getBegin() < m.getMethods().get(i).getBegin()) && (class2.getEnd() > m.getMethods().get(i).getEnd())) {
				class2.addMethod(m.getMethods().get(i));
				class2.setWMC_class(class2.getWMC_class() + m.getMethods().get(i).getCYCLO_method());
				class2.setNOM_class(class2.getNOM_class() + 1);
			}
		}
	}


	public ArrayList<Class> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<Class> classes) {
		this.classes = classes;
	}

	public CompilationUnit getCu() {
		return cu;
	}

	public void setCu(CompilationUnit cu) {
		this.cu = cu;
	}

}