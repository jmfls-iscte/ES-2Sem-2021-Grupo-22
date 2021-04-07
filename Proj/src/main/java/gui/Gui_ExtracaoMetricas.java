package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class Gui_ExtracaoMetricas {

	protected Shell shell;
	private Text dirProj_txt;
	private Text dirExcel_txt;

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
		shell.setSize(895, 546);
		shell.setText("Extração de Métricas");
		shell.setLayout(new GridLayout(2, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label dirProj_lbl = new Label(shell, SWT.NONE);
		dirProj_lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		dirProj_lbl.setText("Diretório do Projeto :");
		dirProj_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		
		dirProj_txt = new Text(shell, SWT.BORDER);
		GridData gd_dirProj_txt = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_dirProj_txt.widthHint = 511;
		dirProj_txt.setLayoutData(gd_dirProj_txt);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label dirExcel_lbl = new Label(shell, SWT.NONE);
		dirExcel_lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		dirExcel_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		dirExcel_lbl.setText("Diretório do Ficheiro Excel :");
		
		dirExcel_txt = new Text(shell, SWT.BORDER);
		dirExcel_txt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button extrairMetricas_btn = new Button(shell, SWT.NONE);
		extrairMetricas_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		extrairMetricas_btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		extrairMetricas_btn.setText("Extrair Métricas");

	}

}
