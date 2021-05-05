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
import org.eclipse.wb.swt.SWTResourceManager;


public class MenuBar extends Composite {

	ProjMainGui mainWindow;
	Button btnNewButton;
	Button btnNewButton_1;
	Button btnNewButton_2;
	Button btnNewButton_3;
	private Label lblNewLabel;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MenuBar(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(54, 33, 89));
		this.mainWindow=mainWindow;
		setLayout(null);
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(26, 10, 139, 28);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel.setText("CQAssessor");
		lblNewLabel.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblNewLabel.setBackground(SWTResourceManager.getColor(54, 33, 89));
		lblNewLabel.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseDoubleClick(MouseEvent e) {
			mainWindow.MainButton();
			resetButtons();
		}
	});
		
				btnNewButton = new Button(this, SWT.BORDER | SWT.FLAT | SWT.TOGGLE);
				btnNewButton.setBounds(5, 65, 195, 49);
				btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
				btnNewButton.setForeground(SWTResourceManager.getColor(255, 255, 255));
				btnNewButton.setBackground(SWTResourceManager.getColor(85,65,118));
				btnNewButton.setImage(SWTResourceManager.getImage("C:\\Users\\ASUS\\git\\ES-2Sem-2021-Grupo-22\\Proj\\Images\\icons8_laptop_metrics_32px_1.png"));
				btnNewButton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
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
				btnNewButton.setText("Extração Métricas");
		
				btnNewButton_1 = new Button(this, SWT.BORDER | SWT.TOGGLE);
				btnNewButton_1.setBounds(5, 119, 195, 49);
				btnNewButton_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
				btnNewButton_1.setForeground(SWTResourceManager.getColor(255, 255, 255));
				btnNewButton_1.setBackground(SWTResourceManager.getColor(85,65,118));
				btnNewButton_1.setImage(SWTResourceManager.getImage("C:\\Users\\ASUS\\git\\ES-2Sem-2021-Grupo-22\\Proj\\Images\\icons8_edit_property_32px.png"));
				btnNewButton_1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
				btnNewButton_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						mainWindow.Button2();
						resetButtons();
					}
				});
						btnNewButton_1.setText("Editor de CodeSmells");
		
				btnNewButton_2 = new Button(this, SWT.BORDER | SWT.TOGGLE);
				btnNewButton_2.setBounds(5, 173, 195, 49);
				btnNewButton_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
				btnNewButton_2.setForeground(SWTResourceManager.getColor(255, 255, 255));
				btnNewButton_2.setBackground(SWTResourceManager.getColor(85,65,118));
				btnNewButton_2.setImage(SWTResourceManager.getImage("C:\\Users\\ASUS\\git\\ES-2Sem-2021-Grupo-22\\Proj\\Images\\icons8_data_quality_32px.png"));
				btnNewButton_2.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
				btnNewButton_2.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});
				btnNewButton_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						mainWindow.Button3();
						resetButtons();
					}
				});
				btnNewButton_2.setText("Qualidade de CodeSmells");
		
				btnNewButton_3 = new Button(this, SWT.BORDER | SWT.TOGGLE);
				btnNewButton_3.setBounds(5, 227, 195, 49);
				btnNewButton_3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
				btnNewButton_3.setForeground(SWTResourceManager.getColor(255, 255, 255));
				btnNewButton_3.setBackground(SWTResourceManager.getColor(85,65,118));
				btnNewButton_3.setImage(SWTResourceManager.getImage("C:\\Users\\ASUS\\git\\ES-2Sem-2021-Grupo-22\\Proj\\Images\\icons8_import_32px_1.png"));
				btnNewButton_3.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
				btnNewButton_3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						mainWindow.Button4();
						resetButtons();
					}
				});
				btnNewButton_3.setText("Importar dados");
				
				Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
				label.setBounds(10, 44, 185, 2);

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
