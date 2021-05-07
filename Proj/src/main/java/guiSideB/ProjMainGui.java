package guiSideB;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

import ana_rules.Rule;
import ana_rules.RuleEvaluator;
import ana_rules.SaveLoadRule;
import excel.ExcelRead;
import metrics.DirectoryGetter;
import metrics.Package;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.wb.swt.SWTResourceManager;

import CodeSmellDetectionEvaluator.CodeSmellDetectionEvaluator;
import CodeSmellDetectionEvaluator.PackageEvaluator;

public class ProjMainGui {

	protected Shell shell;
	protected Display display;
	private Text text;
	private Composite menuBar;
	private WelcomePage welcomePage;
	private GuiExtracaoMetricas metricas;
	private GuiDadosImportados importados;
	private GuiEditorRegras editor;
	private GuiQualidadeRegras qualidade;
	private GuiExportarDados exportar;

	private String ruleFile = "ruleFile";

	private String projPath;
	private List<metrics.Package> packages;
	private List<Rule> rules;
	private String importPath;
	private List<metrics.Package> importedPackages;
	private CodeSmellDetectionEvaluator csde;
	private final String jasmlPath = "jasml_0.10_forEval";
	private final String jasmlCodeSmellPath = "Code_Smells_forEval.xlsx";

	/**
	 * Launches the application
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		try {
			ProjMainGui window = new ProjMainGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the window
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		// shell.pack();
		shell.open();
		shell.layout();
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event event) {

				File myObj = new File(ruleFile);
				SaveLoadRule.SaveRules((ArrayList<Rule>) getRules(), ruleFile);
				// System.out.println(myObj.getAbsolutePath());
			}
		});
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Creates the contents of the window
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(300, 400);
		shell.setSize(1280, 720);

		shell.setImage(SWTResourceManager.getImage("Images\\codeSmell.png"));
		shell.setText("Code Quality Assessor");

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		shell.setLayout(layout);

		menuBar = new MenuBar(shell, SWT.NONE, this);
		GridData gridData = new GridData();
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.widthHint = 205;
		menuBar.setLayoutData(gridData);

		welcomePage = new WelcomePage(shell, SWT.NONE, this);

		setRules(SaveLoadRule.LoadRules(ruleFile));
		if (!assertBaseRules()) {
			setRules(RuleEvaluator.BASERULES());
		}
	}

	/**
	 * Creates the button to the welcome page
	 */
	protected void MainButton() {
		this.disposeAll();

		welcomePage = new WelcomePage(shell, SWT.NONE, this);
		shell.layout();

	}

	/**
	 * Creates the button to GuiExtracaoMetricas
	 */
	protected void Button1() {
		// System.out.println("Button1");
		this.disposeAll();

		metricas = new GuiExtracaoMetricas(shell, SWT.NONE, this);
		metricas.setLayoutData(defaultLayout());
		metricas.firstFill(projPath, packages);
		shell.layout();

	}

	/**
	 * Creates the button to GuiEditorRegras
	 */
	protected void Button2() {
		// System.out.println("Button2");
		this.disposeAll();

		editor = new GuiEditorRegras(shell, SWT.NONE, this);
		editor.setLayoutData(defaultLayout());
		shell.layout();

	}

	/**
	 * Creates the button to GuiQualidadeRegras
	 */
	protected void Button3() {
		// System.out.println("Button3");
		this.disposeAll();

		qualidade = new GuiQualidadeRegras(shell, SWT.NONE, this);
		qualidade.setLayoutData(defaultLayout());
		qualidade.firstFill(csde);
		shell.layout();

	}

	/**
	 * Creates the button to GuiDadosImportados
	 */
	protected void Button4() {
		// System.out.println("Button4");
		this.disposeAll();

		importados = new GuiDadosImportados(shell, SWT.NONE, this);
		importados.setLayoutData(defaultLayout());
		importados.firstFill(importPath, importedPackages);
		shell.layout();

	}

	/**
	 * Creates the button to GuiExportarDados
	 */
	protected void Button5() {
		// System.out.println("Button5");
		this.disposeAll();

		exportar = new GuiExportarDados(shell, SWT.NONE, this);
		exportar.setLayoutData(defaultLayout());
		shell.layout();

	}

	/**
	 * Disposes all guis
	 */
	private void disposeAll() {
		disposeSafe(welcomePage);
		disposeSafe(metricas);
		disposeSafe(importados);
		disposeSafe(editor);
		disposeSafe(qualidade);
		disposeSafe(exportar);
	}

	/**
	 * Disposes the composite
	 * @param composite to dispose
	 */
	private static void disposeSafe(Composite composite) {
		if (composite != null) {
			composite.dispose();
		}
	}

	/**
	 * Gets the shell
	 * @return the shell
	 */
	protected Shell getShell() {
		return shell;
	}

	/**
	 * Sets the default layout
	 * @return the default layout
	 */
	protected static GridData defaultLayout() {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		return gridData;

	}

	/**
	 * Gets the path of the projects
	 * @return the path of the projects
	 */
	protected String getProjPath() {
		return projPath;
	}

	/**
	 * Sets the path of the projects
	 * @param projPath the path of the projects
	 */
	protected void setProjPath(String projPath) {
		this.projPath = projPath;
	}

	/**
	 * Runs the metrics of the project
	 */
	protected void runMetrics() {
		if (projPath != null) {
			// TODO erros apra cada throw do directorygetter
			try {
				DirectoryGetter dirget = new DirectoryGetter();
				dirget.SetDir(projPath);
				dirget.FindSrc();
				dirget.FindPackages();
				packages = dirget.getPackages();
				RuleEvaluator.runCodeSmells(getRules(), packages);

			} catch (IllegalStateException e) {
				MessageBox messageBox = new MessageBox(this.getShell(), SWT.ICON_ERROR | SWT.OK);
				messageBox.setText("Importação de Projeto");
				messageBox.setMessage("O ficheiro selecionado não é um projeto Java \n(Não contem diretoria src)");
				messageBox.open();
			} catch (IllegalArgumentException e) {
				// unreachable;
				e.printStackTrace();
			}
		} else {
			// TODO error pop-up (no project selected)
		}
	}
	
	/**
	 * Runs the excel imported packages
	 */
	protected void runImport() {
		if (importPath != null) {
			ExcelRead excel = new ExcelRead(importPath, (ArrayList<Rule>) getRules());
			importedPackages = excel.ReadFile();
		}
	}
	
	/**
	 * Runs the code smells auto evaluator
	 */
	protected void runCodeSmellAutoEval() {

		ExcelRead excelRead = new ExcelRead(jasmlCodeSmellPath, (ArrayList<Rule>) rules);
		List<Package> packagesExcel = excelRead.ReadFile();

		metrics.DirectoryGetter dirget = new DirectoryGetter();
		dirget.SetDir(jasmlPath);
		dirget.FindSrc();
		dirget.FindPackages();
		List<Package> packagesimport = dirget.getPackageList();
		RuleEvaluator.runCodeSmells(rules, packagesimport);
		csde = new CodeSmellDetectionEvaluator(packagesimport, packagesExcel);

	}

	/**
	 * Gets the packages
	 * @return the packages
	 */
	protected List<metrics.Package> getPackages() {
		return packages;
	}

	/**
	 * Gets the imported packages
	 * @return the imported packages
	 */
	protected List<metrics.Package> getImportedPackages() {
		return importedPackages;
	}

	/**
	 * Gets the path from which to import the packages
	 * @return the path from which to import the packages
	 */
	protected String getImportPath() {
		return importPath;
	}


	/**
	 * Sets the path from which to import the packages
	 * @param importPath the path from which to import the packages
	 */
	protected void setImportPath(String importPath) {
		this.importPath = importPath;
	}
	
	/**
	 * Asserts the base rules
	 * @return the booleans of the godClass and longMethod
	 */
	private boolean assertBaseRules() {
		boolean godclass = false;
		boolean longmethod = false;
		for (Rule rule : getRules()) {
			if (rule.getName().equals("is_God_Class")) {
				godclass = true;
			}
			if (rule.getName().equals("is_Long_Method")) {
				longmethod = true;
			}
		}
		return godclass && longmethod;
	}

	/**
	 * Gets a list of rules
	 * @return a list of rules
	 */
	protected List<Rule> getRules() {
		return rules;
	}


	/**
	 * Sets the list of rules
	 * @param the list of rules
	 */
	protected void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * Gets the code smell detection evaluator
	 * @return the code smell detection evaluator
	 */
	protected CodeSmellDetectionEvaluator getCsde() {
		return csde;
	}
}
