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
		shell.setLayout(new GridLayout(1, false));
		
		Label ExtraoDeMetricas_lbl = new Label(shell, SWT.NONE);
		ExtraoDeMetricas_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		ExtraoDeMetricas_lbl.setText("Extra\u00E7\u00E3o de Metricas");

	}

}
