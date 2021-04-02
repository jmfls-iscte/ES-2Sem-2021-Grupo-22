package ana_rules;

public class RuleObject {
	private String info;
	private String label; //Tipos de labels: name, metric, comparator, threshold, logic_operator 
	
	//criar enumerado com os tipos de label na interface, para ser acessivel a todas as classes e 
	//nao existir repetiçao de codigo
	
	
	public RuleObject(String info, String label){ 
		this.info = info;
		this.label = label; 
	}
	
	public String getInfo() {
		return info;
	}
	
	public String getLabel() {
		return label;
	}
}


