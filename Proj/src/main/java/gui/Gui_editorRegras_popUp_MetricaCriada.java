package gui;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import ana_rules.RuleObject;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Gui_editorRegras_popUp_MetricaCriada extends Composite {

	private Composite shell= this;
	Gui_editorRegras2 mainWindow;
	private Text limite_txt;
	


	public Gui_editorRegras_popUp_MetricaCriada(Composite parent, int style, Gui_editorRegras2 mainWindow) {
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
		comparador_lbl.setBounds(10, 136, 110, 25);
		
		Label limite_lbl = new Label(this, SWT.NONE);
		limite_lbl.setText("Limite :");
		limite_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		limite_lbl.setBounds(64, 191, 56, 25);
		
		limite_txt = new Text(this, SWT.BORDER);
		limite_txt.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		limite_txt.setBounds(126, 188, 245, 31);
		
		Combo comparador_cmb = new Combo(this, SWT.NONE);
		comparador_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		comparador_cmb.setBounds(126, 133, 245, 33);
		comparador_cmb.add("LESS");
		comparador_cmb.add("GREATER");
		comparador_cmb.add("GREATEREQUAL");
		comparador_cmb.add("LESSEQUAL");

		Combo metrica_cmb = new Combo(this, SWT.NONE);
		metrica_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		metrica_cmb.setBounds(126, 65, 245, 33);
		metrica_cmb.add("LOC_METHOD");
		metrica_cmb.add("CYCLO_METHOD");
		metrica_cmb.add("NOM_CLASS");
		metrica_cmb.add("LOC_CLASS");
		metrica_cmb.add("WMC_CLASS");
		
		Label metrica_lbl = new Label(this, SWT.NONE);
		metrica_lbl.setText("Métrica :");
		metrica_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		metrica_lbl.setBounds(53, 68, 67, 25);
		
		Label RegraType_lbl = new Label(this, SWT.NONE);
		RegraType_lbl.setVisible(true);
		RegraType_lbl.setText("Tipo Regra :");
		RegraType_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		RegraType_lbl.setBounds(13, 245, 107, 25);
		
		Combo RegraType_cmb = new Combo(this, SWT.NONE);
		RegraType_cmb.setVisible(true);
		RegraType_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		RegraType_cmb.setBounds(126, 242, 245, 33);
		RegraType_cmb.add("class");
		RegraType_cmb.add("method");
		
		Button Confirm_btn = new Button(this, SWT.NONE);
		Confirm_btn.setText("Criar");
		Confirm_btn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		Confirm_btn.setBounds(126, 299, 90, 30);
		
		


		Confirm_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (metrica_cmb.getText().isBlank() == true || comparador_cmb.getText().isBlank() == true
						|| limite_txt.getText().isBlank() == true ) {

					Gui_editorRegras2.setAviso("Espaços em branco");
				}
				else {
					if (metrica_cmb.getText().compareTo("LOC_METHOD") == 0 || metrica_cmb.getText().compareTo("CYCLO_METHOD") == 0) {
						RuleObject ruleObject = new RuleObject(metrica_cmb.getText(), "METHODMETRIC");
						Gui_editorRegras2.AddToRuleObjects(ruleObject);
					}
					if (metrica_cmb.getText().compareTo("NOM_CLASS") == 0 || metrica_cmb.getText().compareTo("LOC_CLASS") == 0 
							|| metrica_cmb.getText().compareTo("WMC_CLASS") == 0) {
						RuleObject ruleObject = new RuleObject(metrica_cmb.getText(), "CLASSMETRIC");
						Gui_editorRegras2.AddToRuleObjects(ruleObject);
					}

					RuleObject ruleObject1 = new RuleObject(comparador_cmb.getText(), "COMPARISON_OPERATOR");
					Gui_editorRegras2.AddToRuleObjects(ruleObject1);

					RuleObject ruleObject2 = new RuleObject(limite_txt.getText(), "THRESHOLD");
					Gui_editorRegras2.AddToRuleObjects(ruleObject2);

					Gui_editorRegras2.setRegraType(RegraType_cmb.getText());

					Gui_editorRegras2.setAviso("Métrica Criada");
				}

			}
			
		});

	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
