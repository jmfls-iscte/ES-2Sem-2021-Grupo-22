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

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

public class GuiDadosImportados extends Composite {
	
	ProjMainGui mainWindow;
	private Composite shell= this;
	private Text dirProj_txt;
	private Tree tree;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
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
		        
		        directoryDialog.setFilterPath(dirProj_txt.getText());
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
			}
		});
		new Label(composite, SWT.NONE);
		
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
		tree.setHeaderVisible(true);
		tree.pack();
		
	}
	
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
					for(int k=0;k<packages.get(i).getClass_list().get(j).getMethod_list().size();k++) {
						TreeItem ti3 = new TreeItem(ti2, SWT.NONE);
						ti3.setText(0,packages.get(i).getClass_list().get(j).getMethod_list().get(k).getName_method());
						ti3.setText(4,""+packages.get(i).getClass_list().get(j).getMethod_list().get(k).getLOC_method());	
						ti3.setText(5,""+packages.get(i).getClass_list().get(j).getMethod_list().get(k).getCYCLO_method());	
					}
				}
			}	
		}
		else {
			//TODO error pop-up (project empty)
		}
	}
	
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

}
