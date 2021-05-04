package guiSideB;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import CodeSmellDetectionEvaluator.CodeSmellDetectionEvaluator;
import CodeSmellDetectionEvaluator.PackageEvaluator;
import ana_rules.Rule;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Combo;

public class GuiQualidadeRegras extends Composite {
	
	ProjMainGui mainWindow;
	private Text text;
	private Text text_1;
	private Tree tree;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GuiQualidadeRegras(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		GridData gridDatacomposite = new GridData();
		gridDatacomposite.grabExcessHorizontalSpace = true;
		gridDatacomposite.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(gridDatacomposite);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("Avaliação Automática (recomendado)");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("Avaliação Manual");
		new Label(composite, SWT.NONE);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mainWindow.runCodeSmellAutoEval();
				updateTree(mainWindow.getCsde().getPackagesEvaluatorlst());
			}
		});
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_btnNewButton.widthHint = 65;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Avaliar ");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 90;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("Projeto");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label RuleCounter = new Label(composite, SWT.NONE);
		RuleCounter.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		RuleCounter.setText(mainWindow.getRules().size()+" Regras de CodeSmell");
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setText("Excel (manual)");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		GridData gridDatacomposite1 = new GridData();
		gridDatacomposite1.grabExcessHorizontalSpace = true;
		gridDatacomposite1.horizontalAlignment = GridData.FILL;
		gridDatacomposite1.grabExcessVerticalSpace = true;
		gridDatacomposite1.verticalAlignment = GridData.FILL;
		composite_1.setLayoutData(gridDatacomposite1);
		
		tree = new Tree(composite_1, SWT.BORDER);
		GridData gridDatatree = new GridData();
		gridDatatree.grabExcessHorizontalSpace = true;
		gridDatatree.widthHint = 369;
		gridDatatree.horizontalAlignment = GridData.FILL;
		gridDatatree.grabExcessVerticalSpace = true;
		gridDatatree.verticalAlignment = GridData.FILL;
		tree.setLayoutData(gridDatatree);
		
		TreeColumn trclmnPackage = new TreeColumn(tree, SWT.NONE);
		trclmnPackage.setWidth(200);
		trclmnPackage.setText("Package Explorer");
		
		
				
		for(String rule: getColunas(mainWindow.getRules())) {
			TreeColumn coluna = new TreeColumn(tree, SWT.NONE);
			coluna.setWidth(100);
			coluna.setText(rule);
			
		}
		
		tree.setHeaderVisible(true);
		tree.pack();
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		GridData gridDatacomposite2 = new GridData();
		gridDatacomposite2.grabExcessHorizontalSpace = true;
		gridDatacomposite2.horizontalAlignment = GridData.FILL;
		gridDatacomposite2.grabExcessVerticalSpace = true;
		gridDatacomposite2.verticalAlignment = GridData.FILL;
		composite_2.setLayoutData(gridDatacomposite2);
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayout(new GridLayout(4, false));
		GridData gridDatacomposite3 = new GridData();
		gridDatacomposite3.grabExcessHorizontalSpace = true;
		gridDatacomposite3.horizontalAlignment = GridData.FILL;
		composite_3.setLayoutData(gridDatacomposite3);
		
		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setText("New Label");
		new Label(composite_3, SWT.NONE);
		
		Label lblNewLabel_3 = new Label(composite_3, SWT.NONE);
		lblNewLabel_3.setText("New Label");
		
		Label lblNewLabel_4 = new Label(composite_3, SWT.NONE);
		lblNewLabel_4.setText("New Label");
		
		Combo combo = new Combo(composite_3, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_3, SWT.NONE);
		
		Combo combo_2 = new Combo(composite_3, SWT.NONE);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Combo combo_1 = new Combo(composite_3, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
	private void updateTree(List<PackageEvaluator> packages) {
		tree.removeAll();
		if(packages!= null) {
			for (int i = 0; i < packages.size(); i++) {
				//System.out.println("pacotes:"+packages.size());
				TreeItem ti = new TreeItem(tree, SWT.NONE);
				ti.setText(0,packages.get(i).getName());
				for(int j=0;j<packages.get(i).getClasslst().size();j++) {
					//System.out.println("classe:"+packages.get(i).getClasslst().size());
					TreeItem ti2 = new TreeItem(ti, SWT.NONE);
					ti2.setText(0,packages.get(i).getClasslst().get(j).getClasseval().getName_Class());
					//ti2.setText(1,""+packages.get(i).getClasslst().get(j).getClasseval().getLOC_class());
					//ti2.setText(2,""+packages.get(i).getClasslst().get(j).getClasseval().getNOM_class());
					//ti2.setText(3,""+packages.get(i).getClasslst().get(j).getClasseval().getWMC_class());
					for(String rule:packages.get(i).getClasslst().get(j).getCodesmelssEvaluator().keySet()) {
						int colindex=getColIndex(getColunas(mainWindow.getRules()), rule);
						if(colindex!=-1) {
							ti2.setText(colindex,packages.get(i).getClasslst().get(j).getEvalByName(rule).toString());
						}
					}
					for(int k=0;k<packages.get(i).getClasslst().get(j).getMethodslst().size();k++) {
						//System.out.println("metodos:"+packages.get(i).getClasslst().get(j).getMethodslst().size());
						TreeItem ti3 = new TreeItem(ti2, SWT.NONE);
						ti3.setText(0,packages.get(i).getClasslst().get(j).getMethodslst().get(k).getMethodEval().getName_method());
						//ti3.setText(4,""+packages.get(i).getClasslst().get(j).getClasseval().getMethod_list().get(k).getLOC_method());	
						//ti3.setText(5,""+packages.get(i).getClasslst().get(j).getClasseval().getMethod_list().get(k).getCYCLO_method());	
						for(String rule:packages.get(i).getClasslst().get(j).getMethodslst().get(k).getCodesmelssEvaluator().keySet()) {
							int colindex=getColIndex(getColunas(mainWindow.getRules()), rule);
							if(colindex!=-1) {
								ti3.setText(colindex,packages.get(i).getClasslst().get(j).getMethodslst().get(k).getEvalByName(rule).toString());
							}
						}
					}
				}
			}	
		}
		else {
			//TODO error pop-up (project empty)
		}
	}
	
	private static List<String> getColunas(List<Rule> rules) {
		List<String> result= new ArrayList<String>();
		
		for(Rule rule: rules) {
			result.add(rule.getName());
		}
		return result;
	}
	
	private static int getColIndex(List<String> colunas,String title) {
		for(int i=0;i <colunas.size();i++) {
			if(colunas.get(i).equals(title)) {
				return i+1;
			}
		}
		return -1;
	}
	
}
