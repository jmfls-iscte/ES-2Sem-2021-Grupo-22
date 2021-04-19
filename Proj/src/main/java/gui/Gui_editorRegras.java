package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

//import org.apache.commons.codec.language.bm.Rule;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import ana_rules.RuleObject;
import ana_rules.Rule;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Gui_editorRegras {

	protected Shell shell;
	private Combo metrica_cmb;
	private Text Limite_txt;
	private Text regraName_txt;
	
	public ArrayList<RuleObject> ruleObjects = new ArrayList<RuleObject>();
	public ArrayList<Rule> rules = new ArrayList<Rule>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Gui_editorRegras window = new Gui_editorRegras();
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
		
		//Criar a shell
		
		shell = new Shell();
		shell.setSize(1004, 546);
		shell.setText("Editor de Regras");
		shell.setLayout(new GridLayout(7, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		// Criação do label Metricas e combo box associada
		
		Label metrica_lbl = new Label(shell, SWT.NONE);
		metrica_lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		metrica_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		metrica_lbl.setText("Metrica :");
		new Label(shell, SWT.NONE);
		
		metrica_cmb = new Combo(shell, SWT.NONE);
		metrica_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		GridData gd_metrica_cmb = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_metrica_cmb.heightHint = 30;
		gd_metrica_cmb.widthHint = 210;
		metrica_cmb.setLayoutData(gd_metrica_cmb);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
//		metrica_cmb.add("LOC_CLASS");
//		metrica_cmb.add("NOM_CLASS");
//		metrica_cmb.add("WMC_CLASS");
		metrica_cmb.add("LOC_METHOD");
		metrica_cmb.add("CYCLO_METHOD");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		//Criação do label Comparador e combo box associada
		
		Label comparador_lbl = new Label(shell, SWT.NONE);
		comparador_lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		comparador_lbl.setText("Comparador :");
		comparador_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		new Label(shell, SWT.NONE);
		
		Combo comparador_cmb = new Combo(shell, SWT.NONE);
		comparador_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		comparador_cmb.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		comparador_cmb.add("LESS");
		comparador_cmb.add("GREATER");
		comparador_cmb.add("GREATEREQUAL");
		comparador_cmb.add("LESSEQUAL");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		// Criação label Limite e caixa de texto associada
		
		Label limite_lbl = new Label(shell, SWT.NONE);
		limite_lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		limite_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		limite_lbl.setText("Limte :");
		new Label(shell, SWT.NONE);
		
		Limite_txt = new Text(shell, SWT.BORDER);
		Limite_txt.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		Limite_txt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		//Criação do label Operadores Lógicos e combo box associada
		
		Label optLogico = new Label(shell, SWT.NONE);
		optLogico.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		optLogico.setText("Operadores Lógicos :");
		optLogico.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		optLogico.setVisible(false);
		new Label(shell, SWT.NONE);
		
		Combo optL_cmb = new Combo(shell, SWT.NONE);
		optL_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		optL_cmb.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		optL_cmb.setVisible(false);
		
		optL_cmb.add("AND");
		optL_cmb.add("OR");
		new Label(shell, SWT.NONE);
		
		// Criação do botão adicionar metricas à regra
		
		Button AdicionarMetricas_btn = new Button(shell, SWT.NONE);
		AdicionarMetricas_btn.setText("Adicionar Metricas");
		AdicionarMetricas_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		AdicionarMetricas_btn.setVisible(false);
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		// Criação do label Nome Regra e caixa de texto associada
		
		Label regraName_lbl = new Label(shell, SWT.NONE);
		regraName_lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		regraName_lbl.setText("Nome Regra :");
		regraName_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		regraName_lbl.setVisible(false);
		new Label(shell, SWT.NONE);
		
		regraName_txt = new Text(shell, SWT.BORDER);
		regraName_txt.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		regraName_txt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		regraName_txt.setVisible(false);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		//Criação do label Tipo Metrica e combo box associada
		
		Label metricaType_lbl = new Label(shell, SWT.NONE);
		metricaType_lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		metricaType_lbl.setText("Tipo Metrica :");
		metricaType_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		metricaType_lbl.setVisible(false);
		new Label(shell, SWT.NONE);
		
		Combo metricaType_cmb = new Combo(shell, SWT.NONE);
		metricaType_cmb.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		metricaType_cmb.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		metricaType_cmb.setVisible(false);
		
		metricaType_cmb.add("Class");
		metricaType_cmb.add("Method");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		// Botão de Criar Array inicial com Objects para a regra
		
		Button CriarArrayIinicial_btn = new Button(shell, SWT.NONE);
		CriarArrayIinicial_btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		CriarArrayIinicial_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		CriarArrayIinicial_btn.setText("Criar Metrica");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		// Criar Regra botão
		
		Button CriarRegra_btn = new Button(shell, SWT.NONE);
		CriarRegra_btn.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		CriarRegra_btn.setText("Criar Regra");
		
		CriarRegra_btn.setVisible(false);
		
		// Criar o Array inicial para ser usado para criar a regra
		CriarArrayIinicial_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//verificar se existem espaços em branco necessários para criar o array de rule objects inicial
				if (metrica_cmb.getText().isBlank() || comparador_cmb.getText().isBlank()
						|| Limite_txt.getText().isBlank()) {
					
					throw new IllegalArgumentException("Espaços em branco");
				}
				else {	
				RuleObject ruleObject = new RuleObject(metrica_cmb.getText(), "METHODMETRIC");
				ruleObjects.add(ruleObject);
				
				RuleObject ruleObject1 = new RuleObject(comparador_cmb.getText(), "COMPARISON_OPERATOR");
				ruleObjects.add(ruleObject1);
				
				RuleObject ruleObject2 = new RuleObject(Limite_txt.getText(), "THRESHOLD");
				ruleObjects.add(ruleObject2);
				
				System.out.println("metrica criada");
				
				CriarRegra_btn.setVisible(true);
				AdicionarMetricas_btn.setVisible(true);
				optLogico.setVisible(true);
				optL_cmb.setVisible(true);
				regraName_lbl.setVisible(true);
				regraName_txt.setVisible(true);
				metricaType_lbl.setVisible(true);
				metricaType_cmb.setVisible(true);
				CriarArrayIinicial_btn.setVisible(false);
				}
			}
		});
		
		// Adicionar metricas ao array de metricas criado
				// Ter a capacidade de adicionar metricas com AND e OR no comparador lógico
				AdicionarMetricas_btn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDoubleClick(MouseEvent e) {
						if (metrica_cmb.getText().isBlank() || comparador_cmb.getText().isBlank()
								|| Limite_txt.getText().isBlank() || optL_cmb.getText().isBlank()) {
							
							throw new IllegalArgumentException("Espaços em branco");
						}
						RuleObject ruleObject3 = new RuleObject(optL_cmb.getText(), "LOGIC_OPERATOR");
						ruleObjects.add(ruleObject3);
						
						RuleObject ruleObject = new RuleObject(metrica_cmb.getText(), "METHODMETRIC");
						ruleObjects.add(ruleObject);
						
						RuleObject ruleObject1 = new RuleObject(comparador_cmb.getText(), "COMPARISON_OPERATOR");
						ruleObjects.add(ruleObject1);
						
						RuleObject ruleObject2 = new RuleObject(Limite_txt.getText(), "THRESHOLD");
						ruleObjects.add(ruleObject2);
						
						System.out.println("Metrica adicionada");
					}
				});
		
		// Criar a Regra
		CriarRegra_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if (ruleObjects.isEmpty() == false) {
				Rule rule = new Rule(regraName_txt.getText(),metricaType_cmb.getText(),ruleObjects);
				System.out.println("Regra Criada");
				System.out.println(ruleObjects);
				rules.add(rule); // adiciona a regra a um array de regras
				ruleObjects.clear(); //limpa o array inicial criado para poder ser usado novamente para a criação de outras regras
				
				CriarArrayIinicial_btn.setVisible(false);
				
				}
				else {
					throw new IllegalArgumentException("Array de RuleObjects vazio");
				}
			}
		});

	}

}
