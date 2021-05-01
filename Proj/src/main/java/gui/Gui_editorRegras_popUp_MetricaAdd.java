package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class Gui_editorRegras_popUp_MetricaAdd extends Composite {

	private Composite shell= this;
	Gui_editorRegras2 mainWindow;
	private Text limite_txt;
	


	public Gui_editorRegras_popUp_MetricaAdd(Composite parent, int style, Gui_editorRegras2 mainWindow) {
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
		comparador_lbl.setBounds(59, 136, 110, 25);
		
		Label limite_lbl = new Label(this, SWT.NONE);
		limite_lbl.setText("Limite :");
		limite_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		limite_lbl.setBounds(113, 202, 56, 25);
		
		limite_txt = new Text(this, SWT.BORDER);
		limite_txt.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		limite_txt.setBounds(187, 199, 245, 31);
		
		Combo comparador_cmb = new Combo(this, SWT.NONE);
		comparador_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		comparador_cmb.setBounds(187, 133, 245, 33);
		comparador_cmb.add("LESS");
		comparador_cmb.add("GREATER");
		comparador_cmb.add("GREATEREQUAL");
		comparador_cmb.add("LESSEQUAL");

		
		Combo metrica_cmb = new Combo(this, SWT.NONE);
		metrica_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		metrica_cmb.setBounds(187, 65, 245, 33);
		metrica_cmb.add("LOC_METHOD");
		metrica_cmb.add("CYCLO_METHOD");
		metrica_cmb.add("NOM_CLASS");
		metrica_cmb.add("LOC_CLASS");
		metrica_cmb.add("WMC_CLASS");
		
		Label metrica_lbl = new Label(this, SWT.NONE);
		metrica_lbl.setText("Métrica :");
		metrica_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		metrica_lbl.setBounds(102, 68, 67, 25);
		
		Label optLogico = new Label(this, SWT.NONE);
		optLogico.setVisible(true);
		optLogico.setText("Operadores Lógicos :");
		optLogico.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		optLogico.setBounds(0, 256, 169, 25);
		
		Combo optL_cmb = new Combo(this, SWT.NONE);
		optL_cmb.setVisible(true);
		optL_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		optL_cmb.setBounds(187, 253, 245, 33);
		
		Button Confirm_btn = new Button(this, SWT.NONE);
		Confirm_btn.setText("Adicionar");
		Confirm_btn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		Confirm_btn.setBounds(187, 313, 90, 30);
		

	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
