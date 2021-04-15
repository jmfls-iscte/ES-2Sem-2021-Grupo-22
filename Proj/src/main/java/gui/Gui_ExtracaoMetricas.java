package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class Gui_ExtracaoMetricas {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Gui_ExtracaoMetricas window = new Gui_ExtracaoMetricas();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(2,true));
		
		Label ExtraoDeMetricas_lbl = new Label(shell, SWT.NONE);
		ExtraoDeMetricas_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		ExtraoDeMetricas_lbl.setText("Extra\u00E7\u00E3o de Metricas");
		
		Label ExtraoDeMetricas_lbl2 = new Label(shell, SWT.NONE);
		ExtraoDeMetricas_lbl2.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		ExtraoDeMetricas_lbl2.setText("");
		
		Label NOM_Class_lbl = new Label(shell, SWT.NONE);
		NOM_Class_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		NOM_Class_lbl.setText("NOM_Class");
		
		Label NOM_Class_lbl2 = new Label(shell, SWT.NONE);
		NOM_Class_lbl2.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		NOM_Class_lbl2.setText("Result");

		Label WMC_Class_lbl = new Label(shell, SWT.NONE);
		WMC_Class_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		WMC_Class_lbl.setText("WMC_Class");
		
		Label WMC_Class_lbl2 = new Label(shell, SWT.NONE);
		WMC_Class_lbl2.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		WMC_Class_lbl2.setText("Result");
		
		Label LOC_Class_lbl = new Label(shell, SWT.NONE);
		LOC_Class_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		LOC_Class_lbl.setText("LOC_Class");
		
		Label LOC_Class_lbl2 = new Label(shell, SWT.NONE);
		LOC_Class_lbl2.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		LOC_Class_lbl2.setText("Result");
		
		Label LOC_Method_lbl = new Label(shell, SWT.NONE);
		LOC_Method_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		LOC_Method_lbl.setText("LOC_Method");
		
		Label LOC_Method_lbl2 = new Label(shell, SWT.NONE);
		LOC_Method_lbl2.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		LOC_Method_lbl2.setText("Result");
		
		Label CYCLO_Method_lbl = new Label(shell, SWT.NONE);
		CYCLO_Method_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		CYCLO_Method_lbl.setText("CYCLO_Method");
		
		Label CYCLO_Method_lbl2 = new Label(shell, SWT.NONE);
		CYCLO_Method_lbl2.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		CYCLO_Method_lbl2.setText("Result");
	}

}
