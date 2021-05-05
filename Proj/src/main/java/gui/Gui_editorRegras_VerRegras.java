package gui;

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

public class Gui_editorRegras_VerRegras extends Composite {

	private Composite shell= this;
	Gui_editorRegras2 mainWindow;
	private Table table;


	public Gui_editorRegras_VerRegras(Composite parent, int style,Gui_editorRegras2 mainWindow) {
		super(parent, style);
		this.mainWindow = mainWindow;
		setLayout(null);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		table.setBounds(0, 0, 778, 507);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnRegraName = new TableColumn(table, SWT.NONE);
		tblclmnRegraName.setWidth(134);
		tblclmnRegraName.setText("Regra Name");
		
		TableColumn tblclmnRegraType = new TableColumn(table, SWT.NONE);
		tblclmnRegraType.setWidth(124);
		tblclmnRegraType.setText("Regra Type");
		
		TableColumn tblclmnRegraInfo = new TableColumn(table, SWT.NONE);
		tblclmnRegraInfo.setWidth(604);
		tblclmnRegraInfo.setText("Regra Info");
		
		Slider slider = new Slider(this, SWT.NONE);
		slider.setBounds(0, 486, 778, 21);
		
		
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
		updateTree(Gui_editorRegras2.getRule());
		
	}
	private void updateTree(ArrayList<Rule> arrayRules) {
		
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
}
	


