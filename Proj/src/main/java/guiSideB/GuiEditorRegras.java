package guiSideB;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import ana_rules.Rule;
import ana_rules.RuleObject;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class GuiEditorRegras extends Composite {

	ProjMainGui mainWindow;
	
	private Gui_editorRegras_popUp_MetricaCriada metrica_criada;
	private Gui_editorRegras_popUp_MetricaAdd metrica_add;
	private Gui_editorRegras_popUp_RegraCriada regra_criada;
	private Gui_editorRegras_VerRegras ver_regra;
	private GuiRegraEdit editar_regra;
	private GuiEditorRegras self;
	public static List<RuleObject> ruleObjects = new ArrayList<RuleObject>();
	public static String regraType;
	public static String aviso;
	public static MessageBox box;
	Composite composite_Panel;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GuiEditorRegras(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;
		this.self=this;
		setLayout(new GridLayout(1, false));
		
		Composite composite_Buttons = new Composite(this, SWT.NONE);
		GridLayout layout =new GridLayout(4, false);
		layout.makeColumnsEqualWidth=true;
		composite_Buttons.setLayout(layout);
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		composite_Buttons.setLayoutData(gridData);
		
		Button btnCriarMetrica = new Button(composite_Buttons, SWT.NONE);
		GridData gd_btnCriarMetrica = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnCriarMetrica.widthHint = 131;
		btnCriarMetrica.setLayoutData(gd_btnCriarMetrica);
		btnCriarMetrica.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnCriarMetrica.setBounds(21, 10, 111, 30);
		btnCriarMetrica.setText("Criar Métrica");
		
		Button btnAdicionarMetric = new Button(composite_Buttons, SWT.NONE);
		btnAdicionarMetric.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnAdicionarMetric.setText("Adicionar Métrica");
		btnAdicionarMetric.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnAdicionarMetric.setBounds(150, 10, 150, 30);
		
		Button btnCriarRegra = new Button(composite_Buttons, SWT.NONE);
		GridData gd_btnCriarRegra = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnCriarRegra.widthHint = 131;
		btnCriarRegra.setLayoutData(gd_btnCriarRegra);
		btnCriarRegra.setText("Criar Regra");
		btnCriarRegra.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnCriarRegra.setBounds(338, 10, 111, 30);
		
		Button btnMostrarRegras = new Button(composite_Buttons, SWT.NONE);
		GridData gd_btnMostrarRegras = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_btnMostrarRegras.widthHint = 130;
		btnMostrarRegras.setLayoutData(gd_btnMostrarRegras);
		btnMostrarRegras.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnMostrarRegras.setBounds(488, 10, 95, 30);
		btnMostrarRegras.setText("Ver Regras");
		
		box = new MessageBox(self.getShell());
		
		
		
		composite_Panel = new Composite(this, SWT.NONE);
		composite_Panel.setLayout(new GridLayout(1, false));
		composite_Panel.setLayoutData(ProjMainGui.defaultLayout());
		
		btnCriarMetrica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				self.disposeAll();
				metrica_criada = new Gui_editorRegras_popUp_MetricaCriada(composite_Panel,SWT.NONE, self);
				composite_Panel.layout();
				
			}
		});
		
		btnAdicionarMetric.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				self.disposeAll();
				metrica_add = new Gui_editorRegras_popUp_MetricaAdd(composite_Panel,SWT.NONE, self);
				composite_Panel.layout();
				
			}
		});
		
		btnCriarRegra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				self.disposeAll();
				regra_criada = new Gui_editorRegras_popUp_RegraCriada(composite_Panel,SWT.NONE,self);
				composite_Panel.layout();
				
			}
		});
		
		btnMostrarRegras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				verRegras();
//				String ruleinfo = "";
//				Label label1 = new Label(composite_Panel,SWT.NONE);
//				Label label2 = new Label(composite_Panel,SWT.NONE);
//				for ( Rule rule : rules) {
//					label1.setText("Regra: " + rule.getName() + " Type: " + rule.getType());
//					ArrayList<RuleObject> ListRuleObjects = rule.getInfo();
//					for(RuleObject ruleobject : ListRuleObjects) {
//						ruleinfo = ruleinfo + " " + ruleobject.getInfo() + " " + ruleobject.getLabel().toString();
//						ruleobject.getLabel();
//						ruleobject.getInfo();
//						System.out.println(ruleobject.getInfo());
//						System.out.println(ruleobject.getLabel().toString());
//						label2.setText(ruleinfo);
//					}
//				}
				
				
			}
			
			
		});
	}
	private void disposeAll() {
		disposeSafe(metrica_criada);
		disposeSafe(metrica_add);
		disposeSafe(regra_criada);
		disposeSafe(ver_regra);
		disposeSafe(editar_regra);
	}
	private static void disposeSafe(Composite composite) {
		if (composite != null) {
			composite.dispose();
		}
	}
	
	public List<RuleObject> getRuleObjects() {
		return ruleObjects;
	}
	
	public List<Rule> getRule() {
		return mainWindow.getRules();
	}
	
	public void AddToRuleObjects(RuleObject ruleObject) {
		ruleObjects.add(ruleObject);
	}
	
	public void setRegraType(String string) {
		regraType = string;
	}
	
	public String getRegratype() {
		return regraType;
	}
	
	public void AddToRule(Rule rule) {
		mainWindow.getRules().add(rule);
	}
	
	public void ClearRuleObjects() {
		ruleObjects.clear();
		regraType = null;
	}
	
	public void setAviso(String string) {
		aviso = string;
		box.setMessage(aviso);
		box.open();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	protected void editRule(Rule rule) {
		disposeAll();
		editar_regra=new GuiRegraEdit(composite_Panel, SWT.None, this, rule);
		composite_Panel.layout();
	}
	
	protected void verRegras() {
		self.disposeAll();
		ver_regra = new Gui_editorRegras_VerRegras(composite_Panel,SWT.NONE,self);
		composite_Panel.layout();
	}
	

}
