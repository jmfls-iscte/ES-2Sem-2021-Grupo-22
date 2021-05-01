package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import ana_rules.Rule;
import ana_rules.RuleObject;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;

public class Gui_editorRegras2 {

	protected Shell shell;
	private Gui_editorRegras_popUp_MetricaCriada metrica_criada;
	private Gui_editorRegras_popUp_MetricaAdd metrica_add;
	private Gui_editorRegras_popUp_RegraCriada regra_criada;
	private Gui_editorRegras2 shellEditor;
	public static ArrayList<RuleObject> ruleObjects = new ArrayList<RuleObject>();
	public static ArrayList<Rule> rules = new ArrayList<Rule>();
	public static String regraType;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Gui_editorRegras2 window = new Gui_editorRegras2();
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
		shell.setSize(973, 655);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Composite composite_Buttons = new Composite(shell, SWT.BORDER);
		FormData fd_composite_Buttons = new FormData();
		fd_composite_Buttons.bottom = new FormAttachment(0, 123);
		fd_composite_Buttons.right = new FormAttachment(0, 945);
		fd_composite_Buttons.top = new FormAttachment(0, 23);
		fd_composite_Buttons.left = new FormAttachment(0, 10);
		composite_Buttons.setLayoutData(fd_composite_Buttons);
		
		
		Button btnCriarMetrica = new Button(composite_Buttons, SWT.NONE);
		btnCriarMetrica.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnCriarMetrica.setBounds(21, 10, 111, 30);
		btnCriarMetrica.setText("Criar Métrica");
		
		Button btnAdicionarMetric = new Button(composite_Buttons, SWT.NONE);
		btnAdicionarMetric.setText("Adicionar Métrica");
		btnAdicionarMetric.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnAdicionarMetric.setBounds(150, 10, 150, 30);
		
		Composite composite_Panel = new Composite(shell, SWT.BORDER);
		FormData fd_composite_Panel = new FormData();
		fd_composite_Panel.right = new FormAttachment(composite_Buttons, 0, SWT.RIGHT);
		fd_composite_Panel.bottom = new FormAttachment(composite_Buttons, 475, SWT.BOTTOM);
		fd_composite_Panel.top = new FormAttachment(composite_Buttons, 6);
		fd_composite_Panel.left = new FormAttachment(composite_Buttons, 0, SWT.LEFT);
		
		Button btnCriarRegra = new Button(composite_Buttons, SWT.NONE);
		btnCriarRegra.setText("Criar Regra");
		btnCriarRegra.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnCriarRegra.setBounds(338, 10, 111, 30);
		composite_Panel.setLayout(new GridLayout(1, false));
		composite_Panel.setLayoutData(fd_composite_Panel);
		
		
		btnCriarMetrica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				this.disposeAll();
//				Gui_editorRegras_popUp_MetricaCriada gui = new Gui_editorRegras_popUp_MetricaCriada(composite_Panel,SWT.NONE, shellEditor);
				metrica_criada = new Gui_editorRegras_popUp_MetricaCriada(composite_Panel,SWT.NONE, shellEditor);
				composite_Panel.layout();
				
			}

			private void disposeAll() {
				// TODO Auto-generated method stub
				disposeSafe(metrica_criada);
				disposeSafe(metrica_add);
				disposeSafe(regra_criada);
			}
		});
		
		btnAdicionarMetric.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				this.disposeAll();
//				Gui_editorRegras_popUp_MetricaAdd gui = new Gui_editorRegras_popUp_MetricaAdd(composite_Panel,SWT.NONE, shellEditor);
				metrica_add = new Gui_editorRegras_popUp_MetricaAdd(composite_Panel,SWT.NONE, shellEditor);
				composite_Panel.layout();
				
			}
			
			private void disposeAll() {
				// TODO Auto-generated method stub
				disposeSafe(metrica_criada);
				disposeSafe(metrica_add);
				disposeSafe(regra_criada);
			}
		});
		
		btnCriarRegra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				this.disposeAll();
//				Gui_editorRegras_popUp_MetricaAdd gui = new Gui_editorRegras_popUp_MetricaAdd(composite_Panel,SWT.NONE, shellEditor);
				regra_criada = new Gui_editorRegras_popUp_RegraCriada(composite_Panel,SWT.NONE, shellEditor);
				composite_Panel.layout();
				
			}
			
			private void disposeAll() {
				// TODO Auto-generated method stub
				disposeSafe(metrica_criada);
				disposeSafe(metrica_add);
				disposeSafe(regra_criada);
			}
		});
		
		

	}
	
	private static void disposeSafe(Composite composite) {
		if (composite != null) {
			composite.dispose();
		}
	}
	
	public static ArrayList<RuleObject> getRuleObjects() {
		return ruleObjects;
	}
	
	public static ArrayList<Rule> getRule() {
		return rules;
	}
	
	public static void AddToRuleObjects(RuleObject ruleObject) {
		ruleObjects.add(ruleObject);
	}
	
	public static void setRegraType(String string) {
		regraType = string;
	}
	
	public static String getRegratype() {
		return regraType;
	}
	
	public static void AddToRule(Rule rule) {
		rules.add(rule);
	}
	
	public static void ClearRuleObjects() {
		ruleObjects.clear();
	}
	
}
