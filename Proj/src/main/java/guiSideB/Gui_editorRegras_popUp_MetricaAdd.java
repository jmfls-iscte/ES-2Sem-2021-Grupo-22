package guiSideB;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import ana_rules.ClassMetric;
import ana_rules.Comparator_Operator;
import ana_rules.Logical_Operator;
import ana_rules.MethodMetric;
import ana_rules.RuleObject;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

/**
 * 
 * This class is responsible for the gui to generate a pop up when a metric is
 * added
 *
 */
public class Gui_editorRegras_popUp_MetricaAdd extends Composite {

	private Composite shell = this;
	GuiEditorRegras mainWindow;
	private Text limite_txt;

	/**
	 * Creates the composite
	 * 
	 * @param parent     the composite
	 * @param style      number
	 * @param mainWindow the main gui window
	 */
	public Gui_editorRegras_popUp_MetricaAdd(Composite parent, int style, GuiEditorRegras mainWindow) {
		super(parent, style);
		this.mainWindow = mainWindow;
		setLayout(null);

		Composite composite = new Composite(this, SWT.EMBEDDED);
		composite.setLayout(null);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_composite.widthHint = 837;
		composite.setLayoutData(gd_composite);

		Label comparador_lbl = new Label(this, SWT.NONE);
		comparador_lbl.setText("Comparador :");
		comparador_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		comparador_lbl.setBounds(72, 146, 97, 25);

		Label limite_lbl = new Label(this, SWT.NONE);
		limite_lbl.setText("Limite :");
		limite_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		limite_lbl.setBounds(113, 202, 56, 25);

		limite_txt = new Text(this, SWT.BORDER);
		limite_txt.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		limite_txt.setBounds(187, 199, 245, 31);

		Combo comparador_cmb = new Combo(this, SWT.READ_ONLY);
		comparador_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		comparador_cmb.setBounds(187, 146, 245, 33);
		for (Comparator_Operator operator : Comparator_Operator.values()) {
			comparador_cmb.add(operator.getString());
		}

		Combo metrica_cmb = new Combo(this, SWT.READ_ONLY);
		metrica_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		metrica_cmb.setBounds(187, 250, 245, 33);
		List<String> values = new ArrayList<String>();
		if (mainWindow.getRegratype() != null) {
			if (mainWindow.getRegratype().equals("class")) {
				for (ClassMetric metric : ClassMetric.values()) {
					values.add(metric.toString());
				}
			} else {
				for (MethodMetric metric : MethodMetric.values()) {
					values.add(metric.toString());
				}
			}
			String[] r = new String[values.size()];
			metrica_cmb.setItems(values.toArray(r));
		}

		Label metrica_lbl = new Label(this, SWT.NONE);
		metrica_lbl.setText("Métrica :");
		metrica_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		metrica_lbl.setBounds(102, 253, 67, 25);

		Label optLogico = new Label(this, SWT.NONE);
		optLogico.setVisible(true);
		optLogico.setText("Operadores Lógicos :");
		optLogico.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		optLogico.setBounds(23, 101, 146, 25);

		Combo optL_cmb = new Combo(this, SWT.READ_ONLY);
		optL_cmb.setVisible(true);
		optL_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		optL_cmb.setBounds(187, 98, 245, 33);
		for (Logical_Operator operator : Logical_Operator.values()) {
			optL_cmb.add(operator.toString());
		}

		Button Confirm_btn = new Button(this, SWT.NONE);
		Confirm_btn.setText("Adicionar");
		Confirm_btn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		Confirm_btn.setBounds(187, 313, 90, 30);

		Confirm_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

				if (metrica_cmb.getText().isBlank() == true || optL_cmb.getText().isBlank() == true
						|| comparador_cmb.getText().isBlank() == true || limite_txt.getText().isBlank() == true) {

					mainWindow.setAviso("Espaços em branco");

				} else {
					RuleObject ruleObject = new RuleObject(optL_cmb.getText(), "LOGIC_OPERATOR");
					mainWindow.AddToRuleObjects(ruleObject);

					if (metrica_cmb.getText().compareTo("LOC_METHOD") == 0
							|| metrica_cmb.getText().compareTo("CYCLO_METHOD") == 0) {
						RuleObject ruleObject1 = new RuleObject(metrica_cmb.getText(), "METHODMETRIC");
						mainWindow.AddToRuleObjects(ruleObject1);
					}
					if (metrica_cmb.getText().compareTo("NOM_CLASS") == 0
							|| metrica_cmb.getText().compareTo("LOC_CLASS") == 0
							|| metrica_cmb.getText().compareTo("WMC_CLASS") == 0) {
						RuleObject ruleObject1 = new RuleObject(metrica_cmb.getText(), "CLASSMETRIC");
						mainWindow.AddToRuleObjects(ruleObject1);
					}

					RuleObject ruleObject2 = new RuleObject(
							Comparator_Operator.valueOfLabel(comparador_cmb.getText()).toString(),
							"COMPARISON_OPERATOR");
					mainWindow.AddToRuleObjects(ruleObject2);

					RuleObject ruleObject3 = new RuleObject(limite_txt.getText(), "THRESHOLD");
					mainWindow.AddToRuleObjects(ruleObject3);

					mainWindow.setAviso("Métrica Adicionada");

					metrica_cmb.deselectAll();
					comparador_cmb.deselectAll();
					limite_txt.setText("");
					optL_cmb.deselectAll();
				}

			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
