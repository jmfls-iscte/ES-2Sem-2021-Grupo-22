package guiSideB;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class GuiEditorRegras extends Composite {

	
	ProjMainGui mainWindow;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GuiEditorRegras(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
