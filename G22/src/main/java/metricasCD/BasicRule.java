package metricasCD;

import java.util.ArrayList;

public class BasicRule {
	
	private String nome;
	private String metrica1; //proponho que seja definido um Enumerado para definir as metricas, visto que são valores fixos, se for útil 
	private String metrica2;
	private int limite1;
	private int limite2;
	private String operador_logico;
	private String comparacao_simbolo;

	
	
	public BasicRule(String nome, String metrica1, String metrica2, int limite1, int limite2, String operador_logico, String comparacao_simbolo) {
		this.nome = nome;
		this.metrica1 = metrica1;
		this.metrica2 = metrica2;
		this.limite1 = limite1;
		this.limite2 = limite2;
		this.operador_logico = operador_logico;
		this.comparacao_simbolo = comparacao_simbolo;
	
	}
	
	public void edit_limite_1(int a) { 
		this.limite1 = a;
	}
	
	public void edit_limite_2(int a) { 
		this.limite2 = a;
	}

}
