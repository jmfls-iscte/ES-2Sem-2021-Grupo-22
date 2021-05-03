package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import ana_rules.Rule;
import ana_rules.RuleObject;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Gui_editorRegras_popUp_RegraCriada extends Composite {
	
	private Composite shell= this;
	Gui_editorRegras2 mainWindow;
	private Text regraName_txt;

	public Gui_editorRegras_popUp_RegraCriada(Composite parent, int style, Gui_editorRegras2 mainWindow) {
		super(parent, style);
		this.mainWindow = mainWindow;
		setLayout(null);
		
		Label regraName_lbl = new Label(this, SWT.NONE);
		regraName_lbl.setVisible(true);
		regraName_lbl.setText("Nome Regra :");
		regraName_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		regraName_lbl.setBounds(10, 101, 108, 25);
		
		regraName_txt = new Text(this, SWT.BORDER);
		regraName_txt.setVisible(true);
		regraName_txt.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		regraName_txt.setBounds(124, 98, 245, 31);
		
		Button Confirm_btn = new Button(this, SWT.NONE);
		Confirm_btn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		Confirm_btn.setBounds(124, 174, 90, 30);
		Confirm_btn.setText("Criar");
		
		Confirm_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (Gui_editorRegras2.getRuleObjects().isEmpty() == true) {
					Gui_editorRegras2.setAviso("Métrica ainda não foi criada");
				}
				if (regraName_txt.getText().isBlank()) {
					Gui_editorRegras2.setAviso("Regra sem nome");
				}
				else {
					try {
						Rule rule;
						System.out.println(Gui_editorRegras2.getRuleObjects());
						ArrayList<RuleObject> newArray = new ArrayList<>();
						copyArray(newArray,Gui_editorRegras2.getRuleObjects());
						rule = new Rule(regraName_txt.getText(),Gui_editorRegras2.getRegratype(),newArray, true);
						Gui_editorRegras2.AddToRule(rule); // adiciona a regra a um array de regras
						Gui_editorRegras2.ClearRuleObjects(); //limpa o array inicial criado para poder ser usado novamente para a criação de outras regras
						System.out.println(Gui_editorRegras2.getRule().get(0).getInfo());
						Gui_editorRegras2.setAviso("Regra Criada");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private static void copyArray(ArrayList<RuleObject> destino, ArrayList<RuleObject> origem ) {
		for (RuleObject object : origem) {
			destino.add(object);
		}
	}
	
}
