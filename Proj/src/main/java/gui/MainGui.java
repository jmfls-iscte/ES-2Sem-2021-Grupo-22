package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MainGui {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainGui window = new MainGui();
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
		shell.setSize(786, 413);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(3, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button extracao_btn = new Button(shell, SWT.NONE);
		
		GridData gd_extracao_btn = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_extracao_btn.widthHint = 230;
		extracao_btn.setLayoutData(gd_extracao_btn);
		extracao_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		extracao_btn.setText("Extra\u00E7\u00E3o de M\u00E9tricas");
		new Label(shell, SWT.NONE);
		
		Button EditorDeRegras_btn = new Button(shell, SWT.NONE);
		
		EditorDeRegras_btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		EditorDeRegras_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		EditorDeRegras_btn.setText("Editor de Regras");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button dadosI_btn = new Button(shell, SWT.NONE);
		
		dadosI_btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		dadosI_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		dadosI_btn.setText("Dados Importantes");
		new Label(shell, SWT.NONE);
		
		Button DeteoDeCode_btn = new Button(shell, SWT.NONE);
		
		DeteoDeCode_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		DeteoDeCode_btn.setText("Dete\u00E7\u00E3o de Code Smells");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button QualidadeDeCode_btn = new Button(shell, SWT.NONE);
		
		QualidadeDeCode_btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		QualidadeDeCode_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		QualidadeDeCode_btn.setText("Qualidade de Code Smells");
		new Label(shell, SWT.NONE);
		
		extracao_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Gui_ExtracaoMetricas nw1 = new Gui_ExtracaoMetricas();
				nw1.open();
			}
		});
		
		EditorDeRegras_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Gui_editorRegras nw2 = new Gui_editorRegras();
				nw2.open();
			}
		});
		
		dadosI_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Gui_DadosImportantes nw3 = new Gui_DadosImportantes();
				nw3.open();
			}
		});
		
		DeteoDeCode_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Gui_detecaoCS nw4 = new Gui_detecaoCS();
				nw4.open();
			}
		});
		
		QualidadeDeCode_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Gui_qualidadeCS nw5 = new Gui_qualidadeCS();
				nw5.open();
			}
		});

	}

}
