package guiSideB;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import excel.ExcelWrite;
import metrics.DirectoryGetter;
import rules.Rule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


/**
 * 
 * Responsible for the gui to extract the metrics
 *
 */
public class GuiExtracaoMetricas extends Composite {
	
	ProjMainGui mainWindow;
	private Composite shell= this;
	private Text dirProj_txt;
	private Tree tree;
	private Text text;
	private Text exportPath;
	private Button exportButton;
	private Label PackageCounter;
	private Label ClassCounter;
	private Label MethodCounter;
	
	/**
	 * Creates the composite
	 * 
	 * @param parent     the composite
	 * @param style      number
	 * @param mainWindow the main gui window
	 */
	public GuiExtracaoMetricas(Composite parent, int style,ProjMainGui mainWindow) {
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
		btnSelecionarProjeto.setText("Selecionar Projeto");
		btnSelecionarProjeto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(mainWindow.getShell());
				directoryDialog.setFilterPath(dirProj_txt.getText());
				directoryDialog.setMessage("Please select a directory and click OK");

				String dir = directoryDialog.open();
				if (dir != null) {
					dirProj_txt.setText(dir);
					mainWindow.setProjPath(dir);
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
		extrairMetricas_btn.setText("Extrair M??tricas");
		extrairMetricas_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mainWindow.runMetrics();
				updateTree(mainWindow.getPackages());
				updateLabel();
			}
		});
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		composite_2.setLayout(new GridLayout(3, false));
		
		PackageCounter = new Label(composite_2, SWT.NONE);
		GridData gd_PackageCounter = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_PackageCounter.widthHint = 68;
		PackageCounter.setLayoutData(gd_PackageCounter);
		PackageCounter.setText("Package: 0");
		
		ClassCounter = new Label(composite_2, SWT.NONE);
		GridData gd_ClassCounter = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_ClassCounter.widthHint = 61;
		ClassCounter.setLayoutData(gd_ClassCounter);
		ClassCounter.setText("Classe: 0");
		
		MethodCounter = new Label(composite_2, SWT.NONE);
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
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		GridData gridDatacomposite = new GridData();
		gridDatacomposite.grabExcessHorizontalSpace = true;
		gridDatacomposite.horizontalAlignment = GridData.FILL;
		composite_1.setLayoutData(gridDatacomposite);
		
		exportButton = new Button(composite_1, SWT.NONE);
		exportButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if(mainWindow.getPackages()!=null) {
					FileDialog fileSave=new FileDialog(mainWindow.getShell(),SWT.SAVE);
					fileSave.setFilterExtensions(new String[] {"*.xlsx"});
		            fileSave.setFilterPath("c:\\"); // Windows path
		            fileSave.setFileName("your_file_name.xlsx");
		            fileSave.open();
		            String path = fileSave.getFilterPath();
		            String name= fileSave.getFileName();
		            String absolutePath=path+"\\"+name;
		            //System.out.println(absolutePath);
		            File file= new File(absolutePath);
		            if(file.exists()) {
		            	//System.out.println("file");
		            	MessageBox messageBox = new MessageBox(mainWindow.getShell(), SWT.ICON_WARNING | SWT.NO | SWT.YES);
		            	messageBox.setText("Confirmar Exportar");
		                messageBox.setMessage(name+" j?? existe.\nDeseja substitu??-lo?");
		                int fileChoice=messageBox.open();
		                if(fileChoice==SWT.YES) {
		                	if(file.delete()) {
		                		excelExport(absolutePath);
		                		successMessage();
		                	}else {
		                		//can't delete;
		                	}
		                }
		            }else {
		            	excelExport(absolutePath);
		            	successMessage();
		            }
					
				}else {
					System.out.println("UHO");
					//should be unreachable;
				}
			}
		});
		exportButton.setText("Exportar Dados");
		
		exportPath = new Text(composite_1, SWT.BORDER);
		exportPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
	}
	
	/**
	 * Updates the tree in the gui
	 * 
	 * @param packages list of packages
	 */
	private void updateTree(List<metrics.Package> packages) {
		tree.removeAll();
		if(packages!= null) {
			exportSetVisible();
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
								ti3.setText(colindex,packages.get(i).getClass_list().get(j).getMethod_list().get(k).getCsByName(rule).toString());
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
	 * 
	 * @param projPath path of the project
	 * @param packages list of packages
	 */
	protected void firstFill(String projPath,List<metrics.Package> packages) {
		if(projPath!=null) {
			dirProj_txt.setText(projPath);
		}if(packages!=null) {
			updateTree(packages);
			updateLabel();
			exportSetVisible();
		}else {
			exportSetInvisible();
		}
	}

	/**
	 * Sets import buttons visible
	 */
	protected void exportSetVisible() {
		exportButton.setEnabled(true);
		exportPath.setEnabled(true);
	}

	/**
	 * Sets import buttons visible
	 */
	protected void exportSetInvisible() {
		exportButton.setEnabled(false);
		exportPath.setEnabled(false);
	}
	
	/**
	 * Calls excelWrite
	 * 
	 * @param filePath path of the file
	 */
	protected void excelExport(String filePath) {
		ExcelWrite excelWrite= new ExcelWrite();
		try {
			excelWrite.writeFile(filePath, (ArrayList<metrics.Package>)mainWindow.getPackages());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets a list of rules
	 * 
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
	 * 
	 * @param colunas list of columns names
	 * @param title.
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

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Opens a success message
	 */
	private void successMessage() {
		MessageBox messageBox = new MessageBox(mainWindow.getShell(), SWT.ICON_INFORMATION| SWT.OK);
    	messageBox.setText("Exporta????o");
        messageBox.setMessage("Exporta????o concluida com sucesso");
        messageBox.open();
		
	}
	
	/**
	 * Updates Label Counter for Package, Class and Method
	 */
	private void updateLabel() {
		int pacote= mainWindow.getPackages().size();
		int classe=0;
		int metodo=0;
		for(metrics.Package pkt: mainWindow.getPackages()) {
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
