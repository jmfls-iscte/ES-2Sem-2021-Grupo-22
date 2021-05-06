package guiSideB;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import ana_rules.Rule;
import ana_rules.RuleObject;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Gui_editorRegras_VerRegras extends Composite {

	private Composite shell= this;
	GuiEditorRegras mainWindow;
	private Table table;
	private Button edit;
	private Button remove;
	TableColumn tblclmnRegraName;


	public Gui_editorRegras_VerRegras(Composite parent, int style,GuiEditorRegras mainWindow) {
		super(parent, style);
		this.mainWindow = mainWindow;
		setLayout(new GridLayout(2, false));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//System.out.println(table.getSelectionIndex());
				enableButtons();
				
			}
		});
		GridData data=new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1);
		data.grabExcessVerticalSpace=true;
		data.verticalAlignment=SWT.FILL;
		table.setLayoutData(data);
		table.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		tblclmnRegraName = new TableColumn(table, SWT.NONE);
		tblclmnRegraName.setWidth(134);
		tblclmnRegraName.setText("Regra Name");
		
		TableColumn tblclmnRegraType = new TableColumn(table, SWT.NONE);
		tblclmnRegraType.setWidth(124);
		tblclmnRegraType.setText("Regra Type");
		
		TableColumn tblclmnRegraInfo = new TableColumn(table, SWT.NONE);
		tblclmnRegraInfo.setWidth(604);
		tblclmnRegraInfo.setText("Regra Info");
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(ProjMainGui.defaultLayout());
		
		edit = new Button(composite, SWT.NONE);
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Rule rule=Rule.getRuleFromList(mainWindow.getRule(),selectedRule());
				mainWindow.editRule(rule);
				
			}
		});
		GridData gd_edit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_edit.widthHint = 50;
		edit.setLayoutData(gd_edit);
		edit.setText("Editar");
		
		remove = new Button(composite, SWT.NONE);
		remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				disableButtons();
				Rule rule=Rule.getRuleFromList(mainWindow.getRule(),selectedRule());
				mainWindow.getRule().remove(rule);
				updateTree(mainWindow.getRule());
			}
		});
		remove.setText("Apagar");
		
		disableButtons();
	
		
		
//		tree_1 = new Tree(this, SWT.BORDER);
//		GridData gd_tree_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
//		gd_tree_1.widthHint = 767;
//		gd_tree_1.heightHint = 501;
//		tree_1.setLayoutData(gd_tree_1);
//		tree_1.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
//		tree_1.setLinesVisible(true);
//		
//		TreeColumn trclmnRegraExplorer = new TreeColumn(tree_1, SWT.NONE);
//		trclmnRegraExplorer.setWidth(172);
//		trclmnRegraExplorer.setText("Regra Explorer");
//		
//		TreeColumn trclmnRegraType = new TreeColumn(tree_1, SWT.NONE);
//		trclmnRegraType.setWidth(252);
//		trclmnRegraType.setText("Regra Type");
//		
//		TreeColumn trclmnRegraInfo = new TreeColumn(tree_1, SWT.NONE);
//		trclmnRegraInfo.setWidth(347);
//		trclmnRegraInfo.setText("Regra Info");
//		
//		
//
//	
//		
//		tree_1.setHeaderVisible(true);
//		tree_1.pack();
//		
		updateTree(mainWindow.getRule());
		
	}
	private void updateTree(List<Rule> arrayRules) {
		table.removeAll();
		if(arrayRules != null) {
			for ( int i = 0; i < arrayRules.size(); i++) {
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText(0,arrayRules.get(i).getName());
				ti.setText(1,arrayRules.get(i).getType());
				ti.setText(2,arrayRules.get(i).toString());

			}
		}
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void enableButtons() {
		edit.setEnabled(true);
		remove.setEnabled(true);
	}
	
	private void disableButtons() {
		edit.setEnabled(false);
		remove.setEnabled(false);
	}
	
	private String selectedRule() {
		int index=table.getSelectionIndex();
		String rulename=table.getItem(index).getText(0);
		return rulename;
	}
}
	


