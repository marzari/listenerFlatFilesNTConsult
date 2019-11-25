package br.com.ntconsult.testeSelecao.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tiago marzari
 * @since 24/11/2019
 */
@Getter
@Setter
@ToString
public class LineSalesFactory extends Line {

	private long idSale;
	private List<Item> items;
	private String salesman;
	private BigDecimal priceBetterSale;

	/**
	 * @param idSale
	 * @param items
	 * @param salesman
	 */
	public LineSalesFactory(String idSale, List<Item> items, String salesman) {
		super();
		this.idSale = Long.parseLong(idSale);
		this.items = items;
		this.salesman = salesman;
		this.priceBetterSale = this.getCost();
	}

	/**
	 * Retorna o valor da venda mais alta deste grupo de vendas
	 * 
	 * @return
	 */
	public final BigDecimal getCost() {
		return Collections.max(this.items, Comparator.comparing(Item::getCost)).getCost();
	}

	public BigDecimal getSomatorioDaVendas() {
		BigDecimal sum = BigDecimal.ZERO;
		items.forEach((Item item) -> {
			sum.add(item.getCost());
		});
		return sum;
	}

}
