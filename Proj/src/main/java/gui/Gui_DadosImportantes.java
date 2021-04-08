package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

public class Gui_DadosImportantes {

	protected Shell shell;
	private Text excelPath_txt;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Gui_DadosImportantes window = new Gui_DadosImportantes();
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
		shell.setSize(935, 553);
		shell.setText("Dados Importados");
		shell.setLayout(new GridLayout(2, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label excelPath_lbl = new Label(shell, SWT.NONE);
		GridData gd_excelPath_lbl = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_excelPath_lbl.widthHint = 117;
		excelPath_lbl.setLayoutData(gd_excelPath_lbl);
		excelPath_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		excelPath_lbl.setText("Excel Path :");
		
		excelPath_txt = new Text(shell, SWT.BORDER);
		GridData gd_excelPath_txt = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_excelPath_txt.widthHint = 650;
		excelPath_txt.setLayoutData(gd_excelPath_txt);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button mostrarDadosImp_btn = new Button(shell, SWT.NONE);
		mostrarDadosImp_btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		mostrarDadosImp_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		mostrarDadosImp_btn.setText("Mostrar Dados Importados");

	}

}
