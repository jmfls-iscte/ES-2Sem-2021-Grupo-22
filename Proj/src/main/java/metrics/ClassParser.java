package metrics;

import java.util.ArrayList;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * This method reads a java file to create a {@link Class}
 */
public class ClassParser extends VoidVisitorAdapter<Void> {

	private CompilationUnit cu;
	private ArrayList<Class> classes = new ArrayList<Class>();

	/**
	 * This method reads a java file to create a {@link Class}
	 * 
	 * @param n the CompilationUnit
	 * @param arg                         arguments
	 */
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Void arg) {
		super.visit(n, arg);
		VoidVisitor<Void> constructorvisitor = new ConstructorParser();
		((ConstructorParser) constructorvisitor).setCu(getCu());
		constructorvisitor.visit(cu, null);
		VoidVisitor<Void> methodvisitor = new MethodParser();
		((MethodParser) methodvisitor).setCu(getCu());
		methodvisitor.visit(cu, null);
		Class class1 = new Class(n.getName().toString());
		class1.setBegin(n.getBegin().get().line);
		class1.setEnd(n.getEnd().get().line);
		class1.setLOC_class(n.getRange().get().getLineCount());
		classconstructor(class1, (ConstructorParser) constructorvisitor);
		classmethod(class1, (MethodParser) methodvisitor);
		classes.add(class1);

	}

	/**
	 * Associates methods to a class
	 * 
	 * @param class2 class to get methods
	 * @param m      method parser ( class containing all the methods of a java
	 *               file)
	 */
	public void classmethod(Class class2, MethodParser m) {
		for (int i = 0; i < m.getMethods().size(); i++) {
			if ((class2.getBegin() < m.getMethods().get(i).getBegin())
					&& (class2.getEnd() > m.getMethods().get(i).getEnd())) {
				class2.addMethod(m.getMethods().get(i));
				class2.setWMC_class(class2.getWMC_class() + m.getMethods().get(i).getCYCLO_method());
				class2.setNOM_class(class2.getNOM_class() + 1);
			}
		}
	}

	/**
	 * Associates constructors to a class
	 * 
	 * @param class2 class to get constructors
	 * @param m      method parser ( class containing all the constructors of a java
	 *               file)
	 */
	public void classconstructor(Class class2, ConstructorParser m) {
		for (int i = 0; i < m.getMethods().size(); i++) {
			if ((class2.getBegin() < m.getMethods().get(i).getBegin())
					&& (class2.getEnd() > m.getMethods().get(i).getEnd())) {
				class2.addMethod(m.getMethods().get(i));
				class2.setWMC_class(class2.getWMC_class() + m.getMethods().get(i).getCYCLO_method());
				class2.setNOM_class(class2.getNOM_class() + 1);
			}
		}
	}

	/**
	 * Gets an ArrayList of classes in a java file
	 * 
	 * @return an ArrayList of classes in a java file
	 */
	public ArrayList<Class> getClasses() {
		return classes;
	}

	/**
	 * Sets an ArrayList of classes to this ClassParser
	 * 
	 * @param classes an ArrayList of classes
	 */
	public void setClasses(ArrayList<Class> classes) {
		this.classes = classes;
	}

	/**
	 * Gets the CompilationUnit
	 * 
	 * @return CompilationUnit
	 */
	public CompilationUnit getCu() {
		return cu;
	}

	/**
	 * Sets the CompilationUnit
	 * 
	 * @param cu a CompilationUnit
	 */
	public void setCu(CompilationUnit cu) {
		this.cu = cu;
	}

}