package guiSideB;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
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
 * Responsible for the gui of the welcome page
 *
 */
public class WelcomePage extends Composite{

	ProjMainGui mainWindow;
	private Composite shell= this;

	/**
	 * Creates the composite
	 * 
	 * @param parent     the composite
	 * @param style      number
	 * @param mainWindow the main gui window
	 */
	public WelcomePage(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;
		setLayout(new GridLayout(1, false));

		Composite composite = new Composite(this, SWT.EMBEDDED);
		composite.setLayout(new GridLayout(1, false));
		GridData gridDatacomposite1 = new GridData();
		gridDatacomposite1.heightHint = 289;
		gridDatacomposite1.grabExcessHorizontalSpace = true;
		gridDatacomposite1.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(gridDatacomposite1);

		Label lblNewLabel_1 = new Label(composite,  SWT.WRAP);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 690;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setLocation(10, 10);
		lblNewLabel_1.setSize(546, 194);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(240,240,240));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		lblNewLabel_1.setText("A aplicação CodeQualityAssessor tem como objetivo auxiliar as equipas de desenvolvimento de software na\r\ndeteção de defeitos de código. Estes defeitos de código não são bugs (pois não impedem a execução do software),\r\nmas sim representam violações das boas práticas na implementação do software e dificultam a manutenção e\r\nevolução do software por parte das equipas de desenvolvimento.\r\nEstes defeitos são designados na área de engenharia de software por code smells (cheiros no código).");
		new Label(composite, SWT.NONE);
		
		Link link = new Link(composite, SWT.NONE);
		link.setText("<a href=\"https://en.wikipedia.org/wiki/Code_smell\" >What is a CodeSmell?</a>");
		link.addSelectionListener(new SelectionAdapter()  {
			 
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                Program.launch("https://en.wikipedia.org/wiki/Code_smell");
	            }
	             
	        });
	}

	


}
