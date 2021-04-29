package guiSideB;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class GuiExportarDados extends Composite {
	
	ProjMainGui mainWindow;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GuiExportarDados(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;
		
		Label lblWorkinprogress = new Label(this, SWT.NONE);
		lblWorkinprogress.setBounds(168, 142, 55, 15);
		lblWorkinprogress.setText("workinprogress");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
