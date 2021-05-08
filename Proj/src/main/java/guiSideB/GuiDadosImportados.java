package guiSideB;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import rules.Rule;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

/**
 * 
 * Responsible for the gui of the imported data
 *
 */
public class GuiDadosImportados extends Composite {
	
	ProjMainGui mainWindow;
	private Composite shell= this;
	private Text dirProj_txt;
	private Tree tree;
	private Label PackageCounter;
	private Label ClassCounter;
	private Label MethodCounter;

	/**
	 * Creates the composite
	 * @param parent the composite
	 * @param style number
	 * @param mainWindow the main gui window
	 */
	public GuiDadosImportados(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.EMBEDDED);
		composite.setLayout(new GridLayout(2, false));
		GridData gridDatacomposite1 = new GridData();
		gridDatacomposite1.grabExcessHorizontalSpace = true;
		gridDatacomposite1.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(gridDatacomposite1);
		
		Button btnSelecionarProjeto = new Button(composite, SWT.NONE);
		GridData gd_btnSelecionarProjeto = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSelecionarProjeto.widthHint = 107;
		btnSelecionarProjeto.setLayoutData(gd_btnSelecionarProjeto);
		btnSelecionarProjeto.setText("Selecionar Excel");
		btnSelecionarProjeto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog directoryDialog = new FileDialog(mainWindow.getShell());
				directoryDialog.setFilterExtensions(new String[] {"*.xlsx"});
		        directoryDialog.setFilterPath("c:\\");
		        String dir = directoryDialog.open();
			
				if (dir != null) {
					dirProj_txt.setText(dir);
					mainWindow.setImportPath(dir);;
				}
			}
		});
		
		dirProj_txt = new Text(composite, SWT.BORDER);
		dirProj_txt.setEditable(false);
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
	    gridData.grabExcessVerticalSpace = true;
	    gridData.horizontalAlignment = GridData.FILL;
	    gridData.verticalAlignment=GridData.FILL;
		dirProj_txt.setLayoutData(gridData);
		
		Button extrairMetricas_btn = new Button(composite, SWT.NONE);
		GridData gd_extrairMetricas_btn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_extrairMetricas_btn.widthHint = 108;
		extrairMetricas_btn.setLayoutData(gd_extrairMetricas_btn);
		extrairMetricas_btn.setText("Importar Dados");
		extrairMetricas_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mainWindow.runImport();
				updateTree(mainWindow.getImportedPackages());
				updateLabel();
			}
		});
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		composite_1.setLayout(new GridLayout(3, false));
		
		PackageCounter = new Label(composite_1, SWT.NONE);
		GridData gd_PackageCounter = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_PackageCounter.widthHint = 69;
		PackageCounter.setLayoutData(gd_PackageCounter);
		PackageCounter.setText("Package: 0");
		
		ClassCounter = new Label(composite_1, SWT.NONE);
		GridData gd_ClassCounter = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_ClassCounter.widthHint = 55;
		ClassCounter.setLayoutData(gd_ClassCounter);
		ClassCounter.setText("Class: 0");
		
		MethodCounter = new Label(composite_1, SWT.NONE);
		GridData gd_MethodCounter = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_MethodCounter.widthHint = 70;
		MethodCounter.setLayoutData(gd_MethodCounter);
		MethodCounter.setText("Method: 0");
		
		tree = new Tree(this, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TreeColumn trclmnPackage = new TreeColumn(tree, SWT.NONE);
		trclmnPackage.setWidth(200);
		trclmnPackage.setText("Package Explorer");
		
		TreeColumn trclmn1 = new TreeColumn(tree, SWT.NONE);
		trclmn1.setWidth(100);
		trclmn1.setText("LOC_Class");
		
		TreeColumn trclmn2 = new TreeColumn(tree, SWT.NONE);
		trclmn2.setWidth(100);
		trclmn2.setText("NOM_Class");
		
		TreeColumn trclmn3 = new TreeColumn(tree, SWT.NONE);
		trclmn3.setWidth(100);
		trclmn3.setText("WMC_Class");
		
		TreeColumn trclmn4 = new TreeColumn(tree, SWT.NONE);
		trclmn4.setWidth(100);
		trclmn4.setText("LOC_Method");
		
		TreeColumn trclmn5 = new TreeColumn(tree, SWT.NONE);
		trclmn5.setWidth(100);
		trclmn5.setText("CYCLO_Method");
		
		for(String rule: getColunas(mainWindow.getRules())) {
			TreeColumn coluna = new TreeColumn(tree, SWT.NONE);
			coluna.setWidth(100);
			coluna.setText(rule);
			
		}
		
		tree.setHeaderVisible(true);
		tree.pack();
		
	}

	/**
	 * Updates the tree in the gui
	 * 
	 * @param packages list of packages
	 */
	private void updateTree(List<metrics.Package> packages) {
		tree.removeAll();
		
		if(packages!= null) {
			for (int i = 0; i < packages.size(); i++) {
				TreeItem ti = new TreeItem(tree, SWT.NONE);
				ti.setText(0,packages.get(i).getName_Package());
				for(int j=0;j<packages.get(i).getClass_list().size();j++) {
					TreeItem ti2 = new TreeItem(ti, SWT.NONE);
					ti2.setText(0,packages.get(i).getClass_list().get(j).getName_Class());
					ti2.setText(1,""+packages.get(i).getClass_list().get(j).getLOC_class());
					ti2.setText(2,""+packages.get(i).getClass_list().get(j).getNOM_class());
					ti2.setText(3,""+packages.get(i).getClass_list().get(j).getWMC_class());
					for(String rule:packages.get(i).getClass_list().get(j).get_name_code_Smells()) {
						int colindex=getColIndex(getColunas(mainWindow.getRules()), rule);
						if(colindex!=-1) {
							ti2.setText(colindex,packages.get(i).getClass_list().get(j).getCsByName(rule).toString());
						}
					}
					for(int k=0;k<packages.get(i).getClass_list().get(j).getMethod_list().size();k++) {
						TreeItem ti3 = new TreeItem(ti2, SWT.NONE);
						ti3.setText(0,packages.get(i).getClass_list().get(j).getMethod_list().get(k).getName_method());
						ti3.setText(4,""+packages.get(i).getClass_list().get(j).getMethod_list().get(k).getLOC_method());	
						ti3.setText(5,""+packages.get(i).getClass_list().get(j).getMethod_list().get(k).getCYCLO_method());	
						for(String rule:packages.get(i).getClass_list().get(j).getMethod_list().get(k).get_name_code_Smells()) {
							int colindex=getColIndex(getColunas(mainWindow.getRules()), rule);
							if(colindex!=-1) {
								try {
									
									ti3.setText(colindex,packages.get(i).getClass_list().get(j).getMethod_list().get(k).getCsByName(rule).toString());
								} catch (Exception e) {
									// TODO: handle exception
								}
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
	
	/**
	 * Calls updateTree with the path of the project and the package list
	 * @param projPath path of the project
	 * @param packages list of packages
	 */
	protected void firstFill(String projPath,List<metrics.Package> packages) {
		if(projPath!=null) {
			dirProj_txt.setText(projPath);
		}if(packages!=null) {
			updateTree(packages);
		}
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * Gets a list of rules
	 * @param rules list of rules
	 * @return list of rules
	 */
	private static List<String> getColunas(List<Rule> rules) {
		List<String> result= new ArrayList<String>();
		
		for(Rule rule: rules) {
			result.add(rule.getName());
		}
		return result;
	}
	
	/**
	 * Gets the index of the column
	 * @param colunas list of columns names
	 * @param title
	 * @return the index of the column
	 */
	private static int getColIndex(List<String> colunas,String title) {
		for(int i=0;i <colunas.size();i++) {
			if(colunas.get(i).equals(title)) {
				return i+6;
			}
		}
		return -1;
	}
	/**
	 * Updates Label Counter for Package, Class and Method
	 */
	private void updateLabel() {
		int pacote= mainWindow.getImportedPackages().size();
		int classe=0;
		int metodo=0;
		for(metrics.Package pkt: mainWindow.getImportedPackages()) {
			for(metrics.Class cls: pkt.getClass_list()) {
				classe=classe+1;
				metodo=metodo+cls.getMethod_list().size();
			}
		}
		PackageCounter.setText("Package: "+pacote);
		ClassCounter.setText("Class: "+classe);
		MethodCounter.setText("Method: "+metodo);
	}

}
