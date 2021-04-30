package guiSideB;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MenuBar extends Composite {
	
	ProjMainGui mainWindow;
	Button btnNewButton;
	Button btnNewButton_1;
	Button btnNewButton_2;
	Button btnNewButton_3;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MenuBar(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;
		setLayout(new GridLayout(1, false));
		
		btnNewButton = new Button(this, SWT.BORDER | SWT.FLAT | SWT.TOGGLE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mainWindow.Button1();
				resetButtons();
				
				
			}
		});
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_btnNewButton.heightHint = 45;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Extração Métricas");
		
		btnNewButton_1 = new Button(this, SWT.BORDER | SWT.TOGGLE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mainWindow.Button2();
				resetButtons();
			}
		});
		
				GridData gd_btnNewButton_1 = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
				gd_btnNewButton_1.heightHint = 45;
				btnNewButton_1.setLayoutData(gd_btnNewButton_1);
				btnNewButton_1.setText("Editor de CodeSmells");
		
		btnNewButton_2 = new Button(this, SWT.BORDER | SWT.TOGGLE);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mainWindow.Button3();
				resetButtons();
			}
		});
		GridData gd_btnNewButton_2 = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_btnNewButton_2.heightHint = 45;
		btnNewButton_2.setLayoutData(gd_btnNewButton_2);
		btnNewButton_2.setText("Qualidade de CodeSmells");
		
		btnNewButton_3 = new Button(this, SWT.BORDER | SWT.TOGGLE);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mainWindow.Button4();
				resetButtons();
			}
		});
		GridData gd_btnNewButton_3 = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_btnNewButton_3.heightHint = 45;
		btnNewButton_3.setLayoutData(gd_btnNewButton_3);
		btnNewButton_3.setText("Importar dados");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void resetButtons() {
		btnNewButton.setSelection(false);
		btnNewButton_1.setSelection(false);
		btnNewButton_2.setSelection(false);
		btnNewButton_3.setSelection(false);
		//btnNewButton_4.setSelection(false);
	}

}
