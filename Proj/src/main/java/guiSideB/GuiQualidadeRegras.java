package guiSideB;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class GuiQualidadeRegras extends Composite {
	
	ProjMainGui mainWindow;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GuiQualidadeRegras(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;
		
		Label lblDemo = new Label(this, SWT.NONE);
		lblDemo.setBounds(167, 137, 55, 15);
		lblDemo.setText("DEMO");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
