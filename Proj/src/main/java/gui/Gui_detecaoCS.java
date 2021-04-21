package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import ana_rules.Rule;
import ana_rules.RuleEvaluator;
import metrics.DirectoryGetter;
import metrics.Package;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Gui_detecaoCS {

	protected Shell shell;
	private Text ProjPath;
	private List<Rule> temp=new ArrayList<Rule>();
	private Table CStable;
	private List<String> colunas = new ArrayList<String>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Gui_detecaoCS window = new Gui_detecaoCS();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		temp.addAll(RuleEvaluator.BASERULES());
		
		shell = new Shell();
		shell.setSize(904, 571);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(shell, SWT.CENTER);
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lblNewLabel.setText("Deteção de CodeSmells");
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_composite_1.widthHint = 878;
		composite_1.setLayoutData(gd_composite_1);
		
		Button SelectButton = new Button(composite_1, SWT.NONE);
		GridData gd_SelectButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_SelectButton.widthHint = 135;
		SelectButton.setLayoutData(gd_SelectButton);
		SelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(shell);
		        
		        directoryDialog.setFilterPath(ProjPath.getText());
		        directoryDialog.setMessage("Please select a directory and click OK");
		        
		        String dir = directoryDialog.open();
		        if(dir != null) {
		          ProjPath.setText(dir);
		        }
			}
		});
		SelectButton.setText("Selecionar Projeto");
		
		ProjPath = new Text(composite_1, SWT.BORDER);
		GridData gd_ProjPath = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_ProjPath.widthHint = 727;
		ProjPath.setLayoutData(gd_ProjPath);
		ProjPath.setEditable(false);
		
		Button CSButton = new Button(composite_1, SWT.NONE);
		CSButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					String FilePath=ProjPath.getText();
					if(FilePath.equals("")) {
						//TODO erro popup
					}else {
						metrics.DirectoryGetter dirget= new DirectoryGetter();
						dirget.SetDir(FilePath);
						dirget.FindSrc();
						dirget.FindPackages();
						List<Package> packages = dirget.getPackageList();
						RuleEvaluator.runCodeSmells(temp, packages);
						packagePrint(packages,CStable,colunas);
						
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		CSButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		CSButton.setText("Deteção de CodeSmells");
		new Label(composite_1, SWT.NONE);
		
		CStable = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		CStable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		CStable.setHeaderVisible(true);
		CStable.setLinesVisible(true);
		colunas=getColunas(temp);
		getTable(CStable, colunas);

	}
	public static void packagePrint(List<Package> pck,Table CStable,List<String> colunas) {
		for (Package x : pck) {
			//MetricsText.append("\n >"+x.getName_Package());
			TableItem item= new TableItem(CStable, SWT.LEFT);
			item.setText(0,x.getName_Package());
			List<metrics.Class> classlst = x.getClass_list();
			for (metrics.Class c : classlst) {
				//MetricsText.append("\n______________ >"+c.getName_Class());
				//MetricsText.append("\n........................ [Is_God_Class: "+c.getCsByName("Is_God_Class")+"]");					
				//System.out.println("\t" +"\n"+c.getName_Class());
				TableItem item2= new TableItem(CStable, SWT.LEFT);
				item2.setText(0,x.getName_Package());
				item2.setText(1,c.getName_Class());
				for(String rule:c.get_name_code_Smells()) {
					int colindex=getColIndex(colunas, rule);
					if(colindex!=-1) {
						item2.setText(colindex,c.getCsByName(rule).toString());
					}
				}
				List<metrics.Method> methodlst = c.getMethod_list();
				for (metrics.Method m : methodlst) {
					TableItem item3= new TableItem(CStable, SWT.LEFT);
					item3.setText(0,x.getName_Package());
					item3.setText(1,c.getName_Class());
					item3.setText(2,m.getName_method());
					for(String rule:m.get_name_code_Smells()) {
						int colindex=getColIndex(colunas, rule);
						if(colindex!=-1) {
							item3.setText(colindex,m.getCsByName(rule).toString());
						}
					}
					//MetricsText.append("\n____________________________ >"+m.getName_method());
					//MetricsText.append("\n................................................ [Is_long_Method: "+m.getIs_Long_method()+"]");
					
					

				}
			}
		}
	}
	
	private static List<String> getColunas(List<Rule> rules) {
		List<String> result= new ArrayList<String>();
		result.add("Package");
		result.add("Class");
		result.add("Method");
		for(Rule rule: rules) {
			result.add(rule.getName());
		}
		return result;
	}
	
	private static void getTable(Table CStable,List<String> colunas) {
		for(String colunatitle:colunas) {
			TableColumn coluna= new TableColumn(CStable,SWT.CENTER);
			coluna.setText(colunatitle);
			
		}
		for(int i=0;i<colunas.size();i++) {
			CStable.getColumn(i).pack();
		}
	}
	
	private static int getColIndex(List<String> colunas,String title) {
		for(int i=0;i <colunas.size();i++) {
			if(colunas.get(i).equals(title)) {
				return i;
			}
		}
		return -1;
	}
}
