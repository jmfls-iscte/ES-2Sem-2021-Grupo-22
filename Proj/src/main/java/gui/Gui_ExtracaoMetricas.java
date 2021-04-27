package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import metrics.DirectoryGetter;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class Gui_ExtracaoMetricas {

	protected Shell shell;
	private Text dirProj_txt;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Gui_ExtracaoMetricas window = new Gui_ExtracaoMetricas();
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
		shell = new Shell();
		shell.setSize(895, 546);
		shell.setText("Extração de Métricas");
		shell.setLayout(null);

		Button btnSelecionarProjeto = new Button(shell, SWT.NONE);
		btnSelecionarProjeto.setBounds(10, 45, 158, 31);
		btnSelecionarProjeto.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnSelecionarProjeto.setText("Selecionar Projeto");
		btnSelecionarProjeto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(shell);
				directoryDialog.setFilterPath(dirProj_txt.getText());
				directoryDialog.setMessage("Please select a directory and click OK");

				String dir = directoryDialog.open();
				if (dir != null) {
					dirProj_txt.setText(dir);
				}
			}
		});

		dirProj_txt = new Text(shell, SWT.BORDER);
		dirProj_txt.setBounds(174, 47, 647, 27);
		dirProj_txt.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));

		Tree tree = new Tree(shell, SWT.BORDER);
		tree.setBounds(10, 179, 811, 318);
		
//<<<<<<< HEAD
		TreeColumn trclmnPackage = new TreeColumn(tree, SWT.NONE);
		trclmnPackage.setWidth(200);
		trclmnPackage.setText("Package");
		TreeColumn trclmn1 = new TreeColumn(tree, SWT.NONE);
		trclmn1.setWidth(100);
		trclmn1.setText("LOC_Class");
		TreeColumn trclmn2= new TreeColumn(tree, SWT.NONE);
		trclmn2.setWidth(100);
		trclmn2.setText("NOM_Class");
		TreeColumn trclmn3= new TreeColumn(tree, SWT.NONE);
		trclmn3.setWidth(100);
		trclmn3.setText("WMC_Class");
		TreeColumn trclmn4 = new TreeColumn(tree, SWT.NONE);
		trclmn4.setWidth(100);
		trclmn4.setText("LOC_Method");
		TreeColumn trclmn5= new TreeColumn(tree, SWT.NONE);
//=======
//		TreeColumn trclmnPackageExplorer = new TreeColumn(tree, SWT.NONE);
//		TreeColumn trclmn1 = new TreeColumn(tree, SWT.NONE);
//		TreeColumn trclmn2= new TreeColumn(tree, SWT.NONE);
//		TreeColumn trclmn3= new TreeColumn(tree, SWT.NONE);
//		TreeColumn trclmn4 = new TreeColumn(tree, SWT.NONE);
//		TreeColumn trclmn5= new TreeColumn(tree, SWT.NONE);
//		trclmnPackageExplorer.setWidth(400);
//		trclmnPackageExplorer.setText("Package");
//		trclmn1.setWidth(100);
//		trclmn1.setText("LOC_Class");
//		trclmn2.setWidth(100);
//		trclmn2.setText("NOM_Class");
//		trclmn3.setWidth(100);
//		trclmn3.setText("WMC_Class");
//		trclmn4.setWidth(100);
//		trclmn4.setText("LOC_Method");
//>>>>>>> branch 'master' of https://github.com/jmfls-iscte/ES-2Sem-2021-Grupo-22.git
		trclmn5.setWidth(100);
		trclmn5.setText("CYCLO_Method");
		
		Button extrairMetricas_btn = new Button(shell, SWT.NONE);
		extrairMetricas_btn.setBounds(10, 100, 158, 31);
		extrairMetricas_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DirectoryGetter dirget = new DirectoryGetter();
				dirget.SetDir(dirProj_txt.getText());
				dirget.FindSrc();
				dirget.FindPackages();
				List<metrics.Package> packages = dirget.getPackages();
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
		});
		extrairMetricas_btn.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		extrairMetricas_btn.setText("Extrair Métricas");
		
		Label lblPackage = new Label(shell, SWT.NONE);
		lblPackage.setBounds(32, 158, 119, 15);
		lblPackage.setText("Project Explorer");
		
		Label lblLocclass = new Label(shell, SWT.NONE);
		lblLocclass.setBounds(217, 158, 55, 15);
		lblLocclass.setText("LOC_Class");
		
		Label lblNomclass = new Label(shell, SWT.NONE);
		lblNomclass.setBounds(317, 158, 61, 15);
		lblNomclass.setText("NOM_Class");
		
		Label lblWmcclass = new Label(shell, SWT.NONE);
		lblWmcclass.setBounds(416, 158, 67, 15);
		lblWmcclass.setText("WMC_Class");
		
		Label lblLocmethod = new Label(shell, SWT.NONE);
		lblLocmethod.setBounds(517, 158, 75, 15);
		lblLocmethod.setText("LOC_Method");
		
		Label lblCyclomethod = new Label(shell, SWT.NONE);
		lblCyclomethod.setBounds(615, 158, 85, 15);
		lblCyclomethod.setText("CYCLO_Method");
	}
}