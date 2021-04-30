package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;

public class Gui_editorRegras_popUp_MetricaAdd extends Composite {

	private Composite shell= this;
	Gui_editorRegras2 mainWindow;


	public Gui_editorRegras_popUp_MetricaAdd(Composite parent, int style) {
		super(parent, style);
//		this.mainWindow = mainWindow;
		setLayout(null);
		
		Label lblMetrica = new Label(this, SWT.NONE);
		lblMetrica.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblMetrica.setBounds(25, 49, 79, 25);
		lblMetrica.setText("Métrica :");
		
		Combo combo = new Combo(this, SWT.NONE);
		combo.setBounds(127, 46, 97, 28);
		
	}


	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(370, 179);
		shell.setLayout(new GridLayout(3, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label metricaAdd_lbl = new Label(shell, SWT.NONE);
		metricaAdd_lbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		metricaAdd_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		metricaAdd_lbl.setText("Metrica Adiconada");
		

	}
}
