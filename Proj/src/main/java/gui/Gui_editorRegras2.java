package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Gui_editorRegras2 {

	protected Shell shell;
	private Gui_editorRegras_popUp_MetricaAdd metrica_criada;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Gui_editorRegras2 window = new Gui_editorRegras2();
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
		shell.setSize(973, 655);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Composite composite_Buttons = new Composite(shell, SWT.BORDER);
		FormData fd_composite_Buttons = new FormData();
		fd_composite_Buttons.bottom = new FormAttachment(0, 123);
		fd_composite_Buttons.right = new FormAttachment(0, 945);
		fd_composite_Buttons.top = new FormAttachment(0, 23);
		fd_composite_Buttons.left = new FormAttachment(0, 10);
		composite_Buttons.setLayoutData(fd_composite_Buttons);
		
		
		Button btnCriarMetrica = new Button(composite_Buttons, SWT.NONE);
		btnCriarMetrica.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnCriarMetrica.setBounds(21, 10, 111, 30);
		btnCriarMetrica.setText("Criar Métrica");
		
		Button btnAdicionarMetric = new Button(composite_Buttons, SWT.NONE);
		btnAdicionarMetric.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnAdicionarMetric.setText("Adicionar Métrica");
		btnAdicionarMetric.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnAdicionarMetric.setBounds(150, 10, 150, 30);
		
		Composite composite_Panel = new Composite(shell, SWT.BORDER);
		FormData fd_composite_Panel = new FormData();
		fd_composite_Panel.right = new FormAttachment(composite_Buttons, 0, SWT.RIGHT);
		fd_composite_Panel.bottom = new FormAttachment(composite_Buttons, 475, SWT.BOTTOM);
		fd_composite_Panel.top = new FormAttachment(composite_Buttons, 6);
		fd_composite_Panel.left = new FormAttachment(composite_Buttons, 0, SWT.LEFT);
		
		Button btnCriarRegra = new Button(composite_Buttons, SWT.NONE);
		btnCriarRegra.setText("Criar Regra");
		btnCriarRegra.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnCriarRegra.setBounds(338, 10, 111, 30);
		composite_Panel.setLayoutData(fd_composite_Panel);
		
		
		btnCriarMetrica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				metrica_criada = new Gui_editorRegras_popUp_MetricaAdd(composite_Panel,SWT.NONE);
				metrica_criada.setLayoutData(new GridData ( SWT.FILL, SWT.CENTER, true, false,1,1));
				composite_Panel.layout();
				
			}
		});

	}
}
