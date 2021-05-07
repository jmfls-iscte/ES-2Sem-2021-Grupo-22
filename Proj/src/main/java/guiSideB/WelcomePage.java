package guiSideB;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Link;

/**
 * 
 * This class is responsible for the gui of the welcome page
 *
 */
public class WelcomePage extends Composite {

	ProjMainGui mainWindow;
	private Composite shell = this;

	/**
	 * Creates the composite
	 * 
	 * @param parent     the composite
	 * @param style      number
	 * @param mainWindow the main gui window
	 */
	public WelcomePage(Composite parent, int style, ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow = mainWindow;
		setLayout(new GridLayout(1, false));

		Composite composite = new Composite(this, SWT.EMBEDDED);
		composite.setLayout(new GridLayout(1, false));
		GridData gridDatacomposite1 = new GridData();
		gridDatacomposite1.heightHint = 289;
		gridDatacomposite1.grabExcessHorizontalSpace = true;
		gridDatacomposite1.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(gridDatacomposite1);

		Label lblNewLabel_1 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 690;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setLocation(10, 10);
		lblNewLabel_1.setSize(546, 194);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		lblNewLabel_1.setText(
				"A aplicaÃ§Ã£o CodeQualityAssessor tem como objetivo auxiliar as equipas de desenvolvimento de software na\r\ndeteÃ§Ã£o de defeitos de cÃ³digo. Estes defeitos de cÃ³digo nÃ£o sÃ£o bugs (pois nÃ£o impedem a execuÃ§Ã£o do software),\r\nmas sim representam violaÃ§Ãµes das boas prÃ¡ticas na implementaÃ§Ã£o do software e dificultam a manutenÃ§Ã£o e\r\nevoluÃ§Ã£o do software por parte das equipas de desenvolvimento.\r\nEstes defeitos sÃ£o designados na Ã¡rea de engenharia de software por code smells (cheiros no cÃ³digo).");
		new Label(composite, SWT.NONE);

		Link link = new Link(composite, SWT.NONE);
		link.setText("<a>CodeSmell</a>");

		Label lblCodesmell = new Label(composite, SWT.NONE);
		GridData gd_lblCodesmell = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblCodesmell.widthHint = 319;
		gd_lblCodesmell.heightHint = 90;
		lblCodesmell.setLayoutData(gd_lblCodesmell);
		lblCodesmell.setImage(SWTResourceManager.getImage("Images\\icons8_edit_property_32px_1.png"));

	}

}
