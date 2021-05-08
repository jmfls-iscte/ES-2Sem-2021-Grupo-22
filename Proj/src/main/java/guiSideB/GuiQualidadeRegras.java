package guiSideB;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import CodeSmellDetectionEvaluator.CodeSmellDetectionEvaluator;
import CodeSmellDetectionEvaluator.EvaluatorType;
import CodeSmellDetectionEvaluator.PackageEvaluator;
import rules.Rule;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Combo;

/**
 * 
 * Responsible for the gui to extract the metrics
 *
 */
public class GuiQualidadeRegras extends Composite {
	
	ProjMainGui mainWindow;
	private Text text;
	private Text text_1;
	private Tree tree;
	private Label TP;
	private Label FN;
	private Label FP;
	private Label TN;
	private Label Sensitivity;
	private Label Specificity;
	private Label Precision;
	private Label Negative_Predictive_Value;
	private Label Accuracy;
	
	private Combo ComboPackage;
	private Combo ComboRule;
	private Combo ComboClass;
	
	private String defaultChoice=" [ALL] ";
	
	/**
	 * Creates the composite
	 * 
	 * @param parent     the composite
	 * @param style      number
	 * @param mainWindow the main gui window
	 */
	public GuiQualidadeRegras(Composite parent, int style,ProjMainGui mainWindow) {
		super(parent, style);
		this.mainWindow=mainWindow;
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		GridData gridDatacomposite = new GridData();
		gridDatacomposite.grabExcessHorizontalSpace = true;
		gridDatacomposite.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(gridDatacomposite);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("Avaliação Automática (recomendado)");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setText("Avaliação Manual");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mainWindow.runCodeSmellAutoEval();
				updateTree(mainWindow.getCsde().getPackagesEvaluatorlst());
				updateMatrix(defaultChoice, defaultChoice, defaultChoice, mainWindow.getCsde());
				enableCombo();
				fillCombo(ComboClass,new ArrayList<String>());
				fillCombo(ComboPackage,mainWindow.getCsde().getPackagesName());
				fillCombo(ComboRule, mainWindow.getCsde().getRulesName());
			}
		});
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_btnNewButton.widthHint = 81;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Avaliar ");
		
		Button btnNewButton_3 = new Button(composite, SWT.NONE);
		btnNewButton_3.setEnabled(false);
		GridData gd_btnNewButton_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_3.widthHint = 90;
		btnNewButton_3.setLayoutData(gd_btnNewButton_3);
		btnNewButton_3.setText("Avaliar");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setEnabled(false);
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 90;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("Projeto");
		
		text = new Text(composite, SWT.BORDER);
		text.setEnabled(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label RuleCounter = new Label(composite, SWT.NONE);
		RuleCounter.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		RuleCounter.setText(mainWindow.getRules().size()+" Regras de CodeSmell");
		new Label(composite, SWT.NONE);
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setText("Excel (manual)");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setEnabled(false);
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
		composite_2.setLayoutData(defaultLayout());
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		GridLayout composite3grid=new GridLayout(4, false);
		composite3grid.makeColumnsEqualWidth=true;
		composite_3.setLayout(composite3grid);
		GridData gridDatacomposite3 = new GridData();
		gridDatacomposite3.grabExcessHorizontalSpace = true;
		gridDatacomposite3.horizontalAlignment = GridData.FILL;
		composite_3.setLayoutData(gridDatacomposite3);
		
		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setText("Rule:");
		new Label(composite_3, SWT.NONE);
		
		Label lblNewLabel_3 = new Label(composite_3, SWT.NONE);
		lblNewLabel_3.setText("Package:");
		
		Label lblNewLabel_4 = new Label(composite_3, SWT.NONE);
		lblNewLabel_4.setText("Class:");
		
		ComboRule = new Combo(composite_3,  SWT.READ_ONLY);
		ComboRule.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				runFilter();
			}
		});
		ComboRule.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_3, SWT.NONE);
		
		ComboPackage = new Combo(composite_3, SWT.READ_ONLY);
		ComboPackage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!ComboPackage.getText().equals(defaultChoice)) {
					ComboClass.setEnabled(true);
					fillCombo(ComboClass, mainWindow.getCsde().getClassesName(ComboPackage.getText()));
				}else {
					ComboClass.setEnabled(false);
					ComboClass.select(0);
				}
				runFilter();
			}
		});
		ComboPackage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		ComboClass = new Combo(composite_3, SWT.READ_ONLY);
		ComboClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				runFilter();
			}
		});
		ComboClass.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		disableCombo();
		
		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		GridLayout composite4grid=new GridLayout(5, false);
		composite4grid.makeColumnsEqualWidth=true;
		composite_4.setLayout(composite4grid);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		
		Composite composite_2_1 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_2_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_2_1.heightHint = 65;
		gd_composite_2_1.widthHint = 65;
		composite_2_1.setLayoutData(gd_composite_2_1);
		composite_2_1.setLayout(null);
		
		Label lblNewLabel_5 = new Label(composite_2_1, SWT.NONE);
		lblNewLabel_5.setAlignment(SWT.CENTER);
		lblNewLabel_5.setBounds(0, 10, 65, 65);
		lblNewLabel_5.setText("Predicted:\nPositive");
		
		
		Composite composite_3_1 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_3_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_3_1.heightHint = 65;
		gd_composite_3_1.widthHint = 65;
		composite_3_1.setLayoutData(gd_composite_3_1);
		composite_3_1.setLayout(null);
		
		Label lblNewLabel_6 = new Label(composite_3_1, SWT.NONE);
		lblNewLabel_6.setAlignment(SWT.CENTER);
		lblNewLabel_6.setBounds(0, 10, 65, 65);
		lblNewLabel_6.setText("Predicted:\r\nNegative");
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		
		Composite composite_1_2 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_1_2 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_composite_1_2.heightHint = 65;
		gd_composite_1_2.widthHint = 65;
		composite_1_2.setLayoutData(gd_composite_1_2);
		composite_1_2.setSize(200, 200);
		
		Label lblNewLabel_7 = new Label(composite_1_2, SWT.NONE);
		lblNewLabel_7.setAlignment(SWT.CENTER);
		lblNewLabel_7.setBounds(0, 10, 65, 65);
		lblNewLabel_7.setText("Real:\r\nPositive");
				
		Composite composite_2_2 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_2_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_2_2.heightHint = 65;
		gd_composite_2_2.widthHint = 65;
		//composite_2_2.setForeground(new org.eclipse.swt.graphics.Color(getDisplay(), 0, 255, 128));
		composite_2_2.setLayoutData(gd_composite_2_2);
		
		TP = new Label(composite_2_2, SWT.NONE);
		TP.setAlignment(SWT.CENTER);
		TP.setBounds(0, 10, 65, 65);
		TP.setText("TP");
		
		Composite composite_3_2 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_3_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_3_2.heightHint = 65;
		gd_composite_3_2.widthHint = 65;
		composite_3_2.setLayoutData(gd_composite_3_2);
		
		FN = new Label(composite_3_2, SWT.NONE);
		FN.setAlignment(SWT.CENTER);
		FN.setBounds(0, 10, 65, 65);
		FN.setText("FN");
		
		Composite composite_4_2 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_4_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_4_2.heightHint = 65;
		gd_composite_4_2.widthHint = 65;
		composite_4_2.setLayoutData(gd_composite_4_2);
		
		Sensitivity = new Label(composite_4_2, SWT.NONE);
		Sensitivity.setAlignment(SWT.CENTER);
		Sensitivity.setBounds(0, 10, 65, 65);
		Sensitivity.setText("Sensitivity");
		new Label(composite_4, SWT.NONE);
		
		Composite composite_1_3 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_1_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_1_3.heightHint = 65;
		gd_composite_1_3.widthHint = 65;
		composite_1_3.setLayoutData(gd_composite_1_3);
		
		Label lblNewLabel_8 = new Label(composite_1_3, SWT.NONE);
		lblNewLabel_8.setAlignment(SWT.CENTER);
		lblNewLabel_8.setBounds(0, 10, 65, 65);
		lblNewLabel_8.setText("Real:\r\nNegative");
		
		Composite composite_2_3 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_2_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_2_3.heightHint = 65;
		gd_composite_2_3.widthHint = 65;
		composite_2_3.setLayoutData(gd_composite_2_3);
		
		FP = new Label(composite_2_3, SWT.NONE);
		FP.setAlignment(SWT.CENTER);
		FP.setBounds(0, 10, 65, 65);
		FP.setText("TN");
		
		Composite composite_3_3 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_3_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_3_3.heightHint = 65;
		gd_composite_3_3.widthHint = 65;
		composite_3_3.setLayoutData(gd_composite_3_3);
		
		TN = new Label(composite_3_3, SWT.NONE);
		TN.setAlignment(SWT.CENTER);
		TN.setBounds(0, 10, 65, 65);
		TN.setText("TN");
		
		Composite composite_4_3 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_4_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_4_3.heightHint = 65;
		gd_composite_4_3.widthHint = 65;
		composite_4_3.setLayoutData(gd_composite_4_3);
		
		Specificity = new Label(composite_4_3, SWT.NONE);
		Specificity.setAlignment(SWT.CENTER);
		Specificity.setBounds(0, 10, 65, 65);
		Specificity.setText("Specificity");
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		
		Composite composite_2_4 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_2_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_2_4.heightHint = 65;
		gd_composite_2_4.widthHint = 65;
		composite_2_4.setLayoutData(gd_composite_2_4);
		
		Precision = new Label(composite_2_4, SWT.NONE);
		Precision.setAlignment(SWT.CENTER);
		Precision.setBounds(0, 10, 65, 65);
		Precision.setText("Precision");
		
		Composite composite_3_4 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_3_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_3_4.heightHint = 65;
		gd_composite_3_4.widthHint = 65;
		composite_3_4.setLayoutData(gd_composite_3_4);
		
		Negative_Predictive_Value = new Label(composite_3_4, SWT.NONE);
		Negative_Predictive_Value.setAlignment(SWT.CENTER);
		Negative_Predictive_Value.setBounds(0, 0, 65, 65);
		Negative_Predictive_Value.setText("Negative\nPredictive\nValue");
		
		Composite composite_4_4 = new Composite(composite_4, SWT.BORDER);
		GridData gd_composite_4_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_4_4.heightHint = 65;
		gd_composite_4_4.widthHint = 65;
		composite_4_4.setLayoutData(gd_composite_4_4);
		
		Accuracy = new Label(composite_4_4, SWT.NONE);
		Accuracy.setAlignment(SWT.CENTER);
		Accuracy.setBounds(0, 10, 65, 65);
		Accuracy.setText("Accuracy");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * Updates the tree in the gui
	 * 
	 * @param packages list of packages
	 */
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
	
	/**
	 * Updates the matrix
	 * 
	 * @param rule
	 * @param pacote package
	 * @param classe class
	 * @param cdse   code smell detection evaluator
	 */
	private void updateMatrix(String rule, String pacote,String classe,CodeSmellDetectionEvaluator cdse) {
		Map<EvaluatorType, Integer> mapa;
		if(!rule.equals(defaultChoice)&&!pacote.equals(defaultChoice)&&!classe.equals(defaultChoice)) {
			mapa=cdse.getClassificationClassRule(pacote, classe,rule);
		}else if(!pacote.equals(defaultChoice)&&!classe.equals(defaultChoice)) {
			mapa=cdse.getClassificationClass(pacote, classe);
		}else if(!rule.equals(defaultChoice)&&!pacote.equals(defaultChoice)) {
			mapa=cdse.getClassificationPackageRule(pacote, rule);
		}else if(!pacote.equals(defaultChoice)) {
			mapa=cdse.getClassificationPackage(pacote);
		}else if(!rule.equals(defaultChoice)) {
			mapa=cdse.getClassificationRule(rule);
		}else {
			mapa=cdse.getClassificationTotal();
		}
		float tp=mapa.get(EvaluatorType.valueOf("TP"));
		float fn=mapa.get(EvaluatorType.valueOf("FN"));
		float fp=mapa.get(EvaluatorType.valueOf("FP"));
		float tn=mapa.get(EvaluatorType.valueOf("TN"));
		float sens=tp/(tp+fn);
		float spec=tn/(tn+fp);
		float acc=(tp+tn)/(tp+tn+fp+fn);
		float npv=tn/(tn+fn);
		float prec=tp/(tp+fp);
	
		DecimalFormat dfInt = new DecimalFormat();
		dfInt.setMaximumFractionDigits(0);
		TP.setText("TP:\n"+dfInt.format(tp));
		FN.setText("FN:\n"+dfInt.format(fn));
		FP.setText("FP:\n"+dfInt.format(fp));
		TN.setText("TN:\n"+dfInt.format(tn));
		DecimalFormat dfFloat = new DecimalFormat();
		dfFloat.setMaximumFractionDigits(2);
		Sensitivity.setText("Sensitivity:\n"+dfFloat.format(sens*100)+"%");
		Specificity.setText("Specificity:\n"+dfFloat.format(spec*100)+"%");
		Accuracy.setText("Accuracy:\n"+dfFloat.format(acc*100)+"%");
		Negative_Predictive_Value.setText("Negative\nPredictive\nValue:\n"+dfFloat.format(npv*100)+"%");
		Precision.setText("Precision:\n"+dfFloat.format(prec*100)+"%");

		if(tp==0) {
			Sensitivity.setText("Sensitivity:\n--");
			Precision.setText("Precision:\n--");
		}
		if(tn==0) {
			Specificity.setText("Specificity:\n--");
			Negative_Predictive_Value.setText("Negative\nPredictive\nValue:\n--");
		}
		if((tn+tp)==0) {
			Accuracy.setText("Accuracy:\n--");
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
	 * @param title
	 * @return the index of the column
	 */
	private static int getColIndex(List<String> colunas,String title) {
		for(int i=0;i <colunas.size();i++) {
			if(colunas.get(i).equals(title)) {
				return i+1;
			}
		}
		return -1;
	}

	/**
	 * Sets the default layout
	 * 
	 * @return the default layout
	 */
	protected GridData defaultLayout() {
		GridData gridData = new GridData();
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		return gridData;

	}

	/**
	 * Calls updateMatrix and fills comboBox
	 * 
	 * @param cdse code smell detection evaluator
	 */
	protected void firstFill(CodeSmellDetectionEvaluator cdse) {
		if(cdse!=null) {
			updateMatrix(defaultChoice, defaultChoice, defaultChoice, cdse);
			updateTree(cdse.getPackagesEvaluatorlst());
			enableCombo();
			fillCombo(ComboClass,new ArrayList<String>());
			fillCombo(ComboPackage,mainWindow.getCsde().getPackagesName());
			fillCombo(ComboRule, mainWindow.getCsde().getRulesName());
		}
	}
	
	/**
	 * Fills the comboBox
	 * 
	 * @param comboBox to fill
	 * @param options  list of the options
	 */
	private void fillCombo(Combo comboBox,List<String> options) {
		String[] result=new String[options.size()+1];
		result[0]=defaultChoice;
		for(int i=0;i<options.size();i++) {
			result[i+1]=options.get(i);
		}
		comboBox.setItems(result);
		comboBox.select(0);
	}
	
	/***
	 * Disables comboBox
	 */
	private void disableCombo() {
		ComboClass.setEnabled(false);
		ComboPackage.setEnabled(false);
		ComboRule.setEnabled(false);
	}

	/**
	 * Enables the ComboBox
	 */
	private void enableCombo() {
		ComboPackage.setEnabled(true);
		ComboRule.setEnabled(true);
	}
	
	/**
	 * Calls updateMatrix with the code smell detection evaluator
	 */
	private void runFilter() {
		if(mainWindow.getCsde()!=null) {
			String rule=ComboRule.getText();
			String pacote=ComboPackage.getText();
			String classe=ComboClass.getText();
			updateMatrix(rule, pacote, classe, mainWindow.getCsde());
		}
	}
}
