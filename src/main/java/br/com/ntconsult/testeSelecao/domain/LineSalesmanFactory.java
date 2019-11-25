package br.com.ntconsult.testeSelecao.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tiago
 * @since 24/11/2019
 */
@Getter
@Setter
public class LineSalesmanFactory extends Line {

	private String cpf;
	private String name;
	private BigDecimal salary;

	public LineSalesmanFactory(String cpf, String name, String salary) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.salary = new BigDecimal(salary);
	}
	
	@Override
	public String toString() {
		return "Nome: " + name + ", CPF: " + cpf + ", Salário: R$ " + salary.toString().replace(".", ",");
	}

}
