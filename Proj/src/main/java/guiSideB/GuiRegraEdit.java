package guiSideB;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import ana_rules.Rule;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class GuiRegraEdit extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	
	private GuiEditorRegras mainWindow;
	private Rule rule;
	private List<RuleObjectEdit> editlist= new ArrayList<RuleObjectEdit>();
	
	public GuiRegraEdit(Composite parent, int style,GuiEditorRegras mainWindow,Rule rule) {
		super(parent, style);
		this.mainWindow=mainWindow;
		this.rule=rule;
		setLayout(new GridLayout(4, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 85;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("Regra Name");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 89;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setText("Regra Type");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Label RegraName = new Label(this, SWT.NONE);
		RegraName.setAlignment(SWT.CENTER);
		RegraName.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, true, 1, 1));
		RegraName.setText(rule.getName());
		
		Label RegraType = new Label(this, SWT.NONE);
		RegraType.setAlignment(SWT.CENTER);
		RegraType.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, true, 1, 1));
		RegraType.setText(rule.getType());
		
		Composite ruleObjectComposite = new Composite(this, SWT.BORDER);
		ruleObjectComposite.setLayoutData(ProjMainGui.defaultLayout());
		ruleObjectComposite.setLayout(new GridLayout(1, false));
		
		for(int i=0;i<rule.getInfo().size();i=i+4) {
			RuleObjectEdit objectedit= new RuleObjectEdit(ruleObjectComposite, SWT.BORDER, this, rule.getInfo().get(i), rule.getInfo().get(i+1), rule.getInfo().get(i+2));
			editlist.add(objectedit);
		}
		
		ruleObjectComposite.layout();
		
		
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		
		Button SaveButton = new Button(composite_1, SWT.NONE);
		SaveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for(RuleObjectEdit edit: editlist) {
					if(edit.isEdited()) {
						rule.editThreshold(edit.getThreshold(), edit.newObjectInfo());						
					}
				}
				mainWindow.verRegras();
				
			}
		});
		GridData gd_SaveButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_SaveButton.widthHint = 58;
		SaveButton.setLayoutData(gd_SaveButton);
		SaveButton.setText("Guardar");
		
		Button CancelButton = new Button(composite_1, SWT.NONE);
		CancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mainWindow.verRegras();
			}
		});
		CancelButton.setText("Cancelar");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
