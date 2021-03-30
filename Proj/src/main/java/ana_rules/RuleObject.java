package ana_rules;

public class RuleObject {
	public String info;
	public String label; //Tipos de labels: name, metric, comparator, threshold, logic_operator 
	
	//criar enumerado com os tipos de label na interface, para ser acessivel a todas as classes e 
	//nao existir repetiçao de codigo
	
	
	public RuleObject(String info, String label){ 
		this.info = info;
		this.label = label; 
	}
	
	public String get_info() {
		return info;
	}
	
	public String get_label() {
		return label;
	}
}


