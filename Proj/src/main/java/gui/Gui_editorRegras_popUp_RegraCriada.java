package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class Gui_editorRegras_popUp_RegraCriada extends Composite {
	
	private Composite shell= this;
	Gui_editorRegras2 mainWindow;
	private Text regraName_txt;

	public Gui_editorRegras_popUp_RegraCriada(Composite parent, int style, Gui_editorRegras2 mainWindow) {
		super(parent, style);
		this.mainWindow = mainWindow;
		setLayout(null);
		
		Label regraName_lbl = new Label(this, SWT.NONE);
		regraName_lbl.setVisible(true);
		regraName_lbl.setText("Nome Regra :");
		regraName_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		regraName_lbl.setBounds(10, 101, 108, 25);
		
		regraName_txt = new Text(this, SWT.BORDER);
		regraName_txt.setVisible(true);
		regraName_txt.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		regraName_txt.setBounds(124, 98, 245, 31);
		
		Button Confirm_btn = new Button(this, SWT.NONE);
		Confirm_btn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		Confirm_btn.setBounds(124, 174, 90, 30);
		Confirm_btn.setText("Criar");
		
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
