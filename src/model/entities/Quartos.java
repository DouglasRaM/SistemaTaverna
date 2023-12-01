package model.entities;

import java.util.Scanner;

public class Quartos {
	private int Id;
	private String nomeQuarto;
	private String tipo;
	private int camas;
	private double diaria;
	private boolean ocupado;

	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	public String getNomeQuarto() {
		return nomeQuarto;
	}

	public void setNomeQuarto(String nomeQuarto) {
		this.nomeQuarto = nomeQuarto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCamas() {
		return camas;
	}

	public void setCamas(int camas) {
		this.camas = camas;
	}

	public double getDiaria() {
		return diaria;
	}

	public void setDiaria(double diaria) {
		this.diaria = diaria;
	}
	
	public boolean isOcupado() {
		return ocupado;
	}
	
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Quartos() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Qual o nome do quarto?");
		this.setNomeQuarto(scan.nextLine());
		System.out.println("Que tipo de quarto e: " + this.getNomeQuarto() + "?");
		this.setTipo(scan.nextLine());
		System.out.println("Quantas camas esta " + this.getTipo() + " tem?");
		this.setCamas(scan.nextInt());
		System.out.println("Qual o valor da diaria de " + this.getNomeQuarto() + "?");
		this.setDiaria(scan.nextDouble());
		
	}
	
	public Quartos(String nomeQuarto, String tipo, int qtdCamas, double diaria) {
		this.setNomeQuarto(nomeQuarto);
		this.setTipo(tipo);
		this.setCamas(qtdCamas);
		this.setDiaria(diaria);
		System.out.println("done");
	}
	
	public String toString() {
		return "Quarto: "+this.getNomeQuarto()+"; "+this.getTipo()+" com "+this.camas+" camas e "+ this.getDiaria() + " moedas de ouro como diaria!";
	}

}
