package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import ana_rules.Rule;
import ana_rules.RuleObject;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import excel.ExcelRead;

public class Gui_DadosImportantes {

	protected Shell shell;
	private Text excelPath_txt;
	private ArrayList<Rule> rules = new ArrayList<Rule>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Gui_DadosImportantes window = new Gui_DadosImportantes();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
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
		shell.setSize(935, 553);
		shell.setText("Dados Importados");
		shell.setLayout(new GridLayout(2, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label excelPath_lbl = new Label(shell, SWT.NONE);
		GridData gd_excelPath_lbl = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_excelPath_lbl.widthHint = 117;
		excelPath_lbl.setLayoutData(gd_excelPath_lbl);
		excelPath_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		excelPath_lbl.setText("Excel Path :");
		
		excelPath_txt = new Text(shell, SWT.BORDER);
		GridData gd_excelPath_txt = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_excelPath_txt.widthHint = 650;
		excelPath_txt.setLayoutData(gd_excelPath_txt);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button mostrarDadosImp_btn = new Button(shell, SWT.NONE);
		
		mostrarDadosImp_btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		mostrarDadosImp_btn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		mostrarDadosImp_btn.setText("Mostrar Dados Importados");
		
		
		RuleObject obj1 = new RuleObject("LOC_METHOD", "METHODMETRIC");
		RuleObject obj2 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		RuleObject obj3 = new RuleObject("50", "THRESHOLD");
		RuleObject obj4 = new RuleObject("AND", "LOGIC_OPERATOR");
		RuleObject obj5 = new RuleObject("CYCLO_METHOD", "METHODMETRIC");
		RuleObject obj6 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		RuleObject obj7 = new RuleObject("10", "THRESHOLD");
		ArrayList<RuleObject> ruleinfo = new ArrayList<RuleObject>();
		ruleinfo.add(obj1);
		ruleinfo.add(obj2);
		ruleinfo.add(obj3);
		ruleinfo.add(obj4);
		ruleinfo.add(obj5);
		ruleinfo.add(obj6);
		ruleinfo.add(obj7);
		
		RuleObject obj21 = new RuleObject("WMC_CLASS", "CLASSMETRIC");
		RuleObject obj22 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		RuleObject obj23 = new RuleObject("50", "THRESHOLD");
		RuleObject obj24 = new RuleObject("OR", "LOGIC_OPERATOR");
		RuleObject obj25 = new RuleObject("NOM_CLASS", "CLASSMETRIC");
		RuleObject obj26 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		RuleObject obj27 = new RuleObject("10", "THRESHOLD");
		ArrayList<RuleObject> ruleinfo2 = new ArrayList<RuleObject>();
		ruleinfo2.add(obj21);
		ruleinfo2.add(obj22);
		ruleinfo2.add(obj23);
		ruleinfo2.add(obj24);
		ruleinfo2.add(obj25);
		ruleinfo2.add(obj26);
		ruleinfo2.add(obj27);
		
		try {
			Rule longmethod = new Rule ("Is_Long_Method","method",ruleinfo,true);
			Rule godclass = new Rule ("Is_God_Class","class",ruleinfo2,true);
			rules.add(longmethod);
			rules.add(godclass);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		mostrarDadosImp_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
				ExcelRead excel = new ExcelRead(excelPath_txt.getText(),rules);
				excel.ReadFile();
				
			}
		});

	}

}
