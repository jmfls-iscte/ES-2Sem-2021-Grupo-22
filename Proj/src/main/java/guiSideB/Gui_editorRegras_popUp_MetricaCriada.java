package guiSideB;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import rules.ClassMetric;
import rules.Comparator_Operator;
import rules.MethodMetric;
import rules.RuleObject;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 
 * Responsible for the gui to generate a pop up when a metric is
 * created
 *
 */
public class Gui_editorRegras_popUp_MetricaCriada extends Composite {

	private Composite shell= this;
	GuiEditorRegras mainWindow;
	private Text limite_txt;
	private Combo metrica_cmb;
	private Combo RegraType_cmb;
//	private String[] teste;
	

	/**
	 * Creates the composite
	 * 
	 * @param parent     the composite
	 * @param style      number
	 * @param mainWindow the main gui window
	 */
	public Gui_editorRegras_popUp_MetricaCriada(Composite parent, int style, GuiEditorRegras mainWindow) {
		super(parent, style);
		this.mainWindow = mainWindow;
		setLayout(null);
		
		
		Composite composite = new Composite (this, SWT.EMBEDDED);
		composite.setLayout(null);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_composite.widthHint = 837;
		composite.setLayoutData(gd_composite);
		
		Label comparador_lbl = new Label(this, SWT.NONE);
		comparador_lbl.setText("Comparador :");
		comparador_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		comparador_lbl.setBounds(13, 195, 110, 25);
		
		Label limite_lbl = new Label(this, SWT.NONE);
		limite_lbl.setText("Limite :");
		limite_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		limite_lbl.setBounds(67, 239, 56, 25);
		
		limite_txt = new Text(this, SWT.BORDER);
		limite_txt.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		limite_txt.setBounds(126, 236, 245, 31);
		
		Combo comparador_cmb = new Combo(this, SWT.READ_ONLY);
		comparador_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		comparador_cmb.setBounds(126, 192, 245, 33);
		for (Comparator_Operator operator : Comparator_Operator.values()) {
			comparador_cmb.add(operator.getString());
		}

		metrica_cmb = new Combo(this, SWT.READ_ONLY);
		metrica_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		metrica_cmb.setBounds(126, 150, 245, 33);
		metrica_cmb.setEnabled(false);
		
		
		Label metrica_lbl = new Label(this, SWT.NONE);
		metrica_lbl.setText("Métrica :");
		metrica_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		metrica_lbl.setBounds(56, 153, 67, 25);
		
		Label RegraType_lbl = new Label(this, SWT.NONE);
		RegraType_lbl.setVisible(true);
		RegraType_lbl.setText("Tipo Regra :");
		RegraType_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		RegraType_lbl.setBounds(16, 114, 107, 25);
		
		RegraType_cmb = new Combo(this, SWT.READ_ONLY);
		RegraType_cmb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				metrica_cmb.setEnabled(true);
				List<String> values = new ArrayList<String>();
				if (RegraType_cmb.getText().equals("class")) {
					for(ClassMetric metric : ClassMetric.values() ) {
						values.add(metric.toString());
					}
				}
				else {
					for(MethodMetric metric : MethodMetric.values()) {
						values.add(metric.toString());
					}
				}
				String[] r = new String[values.size()];
				metrica_cmb.setItems(values.toArray(r));

			}
		});
		RegraType_cmb.setVisible(true);
		RegraType_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.READ_ONLY));
		RegraType_cmb.setBounds(126, 111, 245, 33);
		RegraType_cmb.add("class");
		RegraType_cmb.add("method");
		
		Button Confirm_btn = new Button(this, SWT.NONE);
		Confirm_btn.setText("Criar");
		Confirm_btn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		Confirm_btn.setBounds(126, 299, 90, 30);
		if(mainWindow.getRegratype() != null) {
			Confirm_btn.setEnabled(false);
		}
		
		
		Confirm_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (metrica_cmb.getText().isBlank() == true || comparador_cmb.getText().isBlank() == true
						|| limite_txt.getText().isBlank() == true ) {

					mainWindow.setAviso("Espaços em branco");
				}
				else {
					if (metrica_cmb.getText().compareTo("LOC_METHOD") == 0 || metrica_cmb.getText().compareTo("CYCLO_METHOD") == 0) {
						RuleObject ruleObject = new RuleObject(metrica_cmb.getText(), "METHODMETRIC");
						mainWindow.AddToRuleObjects(ruleObject);
					}
					if (metrica_cmb.getText().compareTo("NOM_CLASS") == 0 || metrica_cmb.getText().compareTo("LOC_CLASS") == 0 
							|| metrica_cmb.getText().compareTo("WMC_CLASS") == 0) {
						RuleObject ruleObject = new RuleObject(metrica_cmb.getText(), "CLASSMETRIC");
						mainWindow.AddToRuleObjects(ruleObject);
					}

					RuleObject ruleObject1 = new RuleObject(Comparator_Operator.valueOfLabel(comparador_cmb.getText()).toString(), "COMPARISON_OPERATOR");
					mainWindow.AddToRuleObjects(ruleObject1);

					RuleObject ruleObject2 = new RuleObject(limite_txt.getText(), "THRESHOLD");
					mainWindow.AddToRuleObjects(ruleObject2);

					mainWindow.setRegraType(RegraType_cmb.getText());

					mainWindow.setAviso("Métrica Criada");
					
					Confirm_btn.setEnabled(false);
				}

			}
			
		});

	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
