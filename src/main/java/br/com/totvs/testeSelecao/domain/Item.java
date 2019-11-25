package br.com.totvs.testeSelecao.domain;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tiago marzari
 * @since 24/11/2019
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Item {

	private Long idItem;
	private Integer quantity;
	private BigDecimal price;

	/**
	 * @param idItem
	 * @param quantity
	 * @param price
	 */
	public Item(String idItem, String quantity, BigDecimal price) {
		super();
		this.idItem = Long.parseLong(idItem);
		this.quantity = Integer.parseInt(quantity);
		this.price = price;
	}

	public BigDecimal getCost() {
		return this.price.multiply(new BigDecimal(this.quantity));
	}

}
