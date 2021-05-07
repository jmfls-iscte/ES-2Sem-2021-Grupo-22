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

	private String projPath = "C:\\Users\\jose1\\eclipse-workspace\\guard";
	private List<metrics.Package> packages;
	private List<Rule> rules;
	private String importPath;
	private List<metrics.Package> importedPackages;
	private CodeSmellDetectionEvaluator csde;
	private final String jasmlPath = "jasml_0.10_forEval";
	private final String jasmlCodeSmellPath = "Code_Smells_forEval.xlsx";

	/**
	 * Launch the application.
	 * 
	 * @param args
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
	 * Open the window.
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
	 * Create contents of the window.
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

	protected void MainButton() {
		this.disposeAll();

		welcomePage = new WelcomePage(shell, SWT.NONE, this);
		shell.layout();

	}

	protected void Button1() {
		// System.out.println("Button1");
		this.disposeAll();

		metricas = new GuiExtracaoMetricas(shell, SWT.NONE, this);
		metricas.setLayoutData(defaultLayout());
		metricas.firstFill(projPath, packages);
		shell.layout();

	}

	protected void Button2() {
		// System.out.println("Button2");
		this.disposeAll();

		editor = new GuiEditorRegras(shell, SWT.NONE, this);
		editor.setLayoutData(defaultLayout());
		shell.layout();

	}

	protected void Button3() {
		// System.out.println("Button3");
		this.disposeAll();

		qualidade = new GuiQualidadeRegras(shell, SWT.NONE, this);
		qualidade.setLayoutData(defaultLayout());
		qualidade.firstFill(csde);
		shell.layout();

	}

	protected void Button4() {
		// System.out.println("Button4");
		this.disposeAll();

		importados = new GuiDadosImportados(shell, SWT.NONE, this);
		importados.setLayoutData(defaultLayout());
		importados.firstFill(importPath, importedPackages);
		shell.layout();

	}

	protected void Button5() {
		// System.out.println("Button5");
		this.disposeAll();

		exportar = new GuiExportarDados(shell, SWT.NONE, this);
		exportar.setLayoutData(defaultLayout());
		shell.layout();

	}

	private void disposeAll() {
		disposeSafe(welcomePage);
		disposeSafe(metricas);
		disposeSafe(importados);
		disposeSafe(editor);
		disposeSafe(qualidade);
		disposeSafe(exportar);
	}

	private static void disposeSafe(Composite composite) {
		if (composite != null) {
			composite.dispose();
		}
	}

	protected Shell getShell() {
		return shell;
	}

	protected static GridData defaultLayout() {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		return gridData;

	}

	protected String getProjPath() {
		return projPath;
	}

	protected void setProjPath(String projPath) {
		this.projPath = projPath;
	}

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

	protected void runImport() {
		if (importPath != null) {
			ExcelRead excel = new ExcelRead(importPath, (ArrayList<Rule>) getRules());
			importedPackages = excel.ReadFile();
		}
	}

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

	protected List<metrics.Package> getPackages() {
		return packages;
	}

	protected List<metrics.Package> getImportedPackages() {
		return importedPackages;
	}

	protected String getImportPath() {
		return importPath;
	}

	protected void setImportPath(String importPath) {
		this.importPath = importPath;
	}

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

	protected List<Rule> getRules() {
		return rules;
	}

	protected void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	protected CodeSmellDetectionEvaluator getCsde() {
		return csde;
	}
}
