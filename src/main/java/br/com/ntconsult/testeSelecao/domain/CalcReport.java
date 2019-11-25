package br.com.ntconsult.testeSelecao.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tiago marzari
 * @since 24/11/2018
 */
@Setter
@Getter
public class CalcReport {
	
	private long idMmostExpensiveSale;
	private long sumBuyer;
	private long sumSaleman;
	private LineSalesmanFactory worstSalesman;
	
	/**
	 * @param idMmostExpensiveSale
	 * @param sumBuyer
	 * @param sumSaleman
	 * @param worstSalesman
	 */
	public CalcReport(long idMmostExpensiveSale, long sumBuyer, long sumSaleman, Line worstSalesman) {
		super();
		this.idMmostExpensiveSale = idMmostExpensiveSale;
		this.sumBuyer = sumBuyer;
		this.sumSaleman = sumSaleman;
		this.worstSalesman = (LineSalesmanFactory) worstSalesman;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RELATÓRIO:");
		builder.append("\nID da venda mais cara: ");
		builder.append(idMmostExpensiveSale);
		builder.append("\nQuantidade de clientes no arquivo de entrada: ");
		builder.append(sumBuyer);
		builder.append("\nQuantidade de vendedores no arquivo de entrada: ");
		builder.append(sumSaleman);
		builder.append("\nVendedor com menos vendas: ");
		builder.append(worstSalesman.getName());
		return builder.toString();
	}

}
