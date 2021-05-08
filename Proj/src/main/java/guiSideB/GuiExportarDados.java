package guiSideB;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;

/**
 * 
 * Responsible for the gui to export data
 *
 */
public class GuiExportarDados extends Composite {
	
	ProjMainGui mainWindow;

	/**
	 * Creates the composite
	 * 
	 * @param parent     the composite
	 * @param style      number
	 * @param mainWindow the main gui window
	 */
	public GuiExportarDados(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;
		setLayout(new GridLayout(2, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 55, 15);
		lblNewLabel.setText("Projeto:");
		
		List<metrics.Package> packages= mainWindow.getPackages();
		
		if(packages==null) {
			Label lblNewLabel2 = new Label(composite, SWT.NONE);
			lblNewLabel2.setBounds(0, 0, 55, 15);
			lblNewLabel2.setText("Sem projeto carregado");
			lblNewLabel2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		}else {
			Tree tree = new Tree(composite, SWT.BORDER);
			tree.setLayoutData(mainWindow.defaultLayout());
		}
		
		
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
